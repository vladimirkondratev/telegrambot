package ru.cityinfo;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger log = getLogger(Bot.class);

    private static Bot bot;

    private Bot() {
    }

    public static Bot getInstance() {
        if (bot == null) {
            bot = new Bot();
        }
        return bot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
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
        return "TravelCityInfo_bot";
    }

    @Override
    public String getBotToken() {
        return "1291049500:AAGL0VfHiOgyoVmwbOIEkAOqvepHHMvGb-A";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(Bot.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
