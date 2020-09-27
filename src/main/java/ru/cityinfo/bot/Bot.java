package ru.cityinfo.bot;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.cityinfo.service.BotService;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger log = getLogger(Bot.class);

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Autowired
    private BotService botService;

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String response = botService.retrieveCityInfos(message);
        sendMsg(update.getMessage().getChatId().toString(), response);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.debug("Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
