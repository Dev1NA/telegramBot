package com.telegramBot;

import com.telegramBot.repo.Repo;
import com.telegramBot.repo.UserFromTelegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainBot extends TelegramLongPollingBot {

    private Repo repo;

    @Autowired
    public void initRepo(Repo repo) {
        this.repo = repo;
    }

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private static String callback_username = "user_name";

    public static SendMessage sendInlineKeyBoardMessage(long chatId, String answer){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Save username");
        inlineKeyboardButton1.setCallbackData(callback_username);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(chatId));
        sm.setText(answer);
        sm.setReplyMarkup(inlineKeyboardMarkup);
        return sm;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            try {
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), "Hello, bro-" + update.getMessage().getFrom().getFirstName() + "!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {
            String str = update.getCallbackQuery().getData();
            if (str.equals(callback_username)) {
                repo.save(new UserFromTelegram(update.getCallbackQuery().getFrom().getFirstName()));
            }
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
