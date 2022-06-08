package com.telegramBot;

import com.telegramBot.repo.Repo;
import com.telegramBot.repo.UserFromTelegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

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
    private static String capricorn = "capricorn";
    private static String aquarius = "aquarius";
    private static String pisces = "pisces";
    private static String aries = "aries";
    private static String taurus = "taurus";
    private static String gemini = "gemini";
    private static String cancer = "cancer";
    private static String leo = "leo";
    private static String virgo = "virgo";
    private static String libra = "libra";
    private static String scorpio = "scorpio";
    private static String sagittarius = "sagittarius";


    private static String back = "back";



    public static SendMessage backButton(long chatId, String answer) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Повернутись назад");
        inlineKeyboardButton1.setCallbackData(back);

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



    public static SendMessage sendInlineKeyBoardMessage(long chatId, String answer){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton6 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton7 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton8 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton9 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton10 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton11 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton12 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Capricorn");
        inlineKeyboardButton1.setCallbackData(capricorn);

        inlineKeyboardButton2.setText("Aquarius");
        inlineKeyboardButton2.setCallbackData(aquarius);

        inlineKeyboardButton3.setText("Pisces");
        inlineKeyboardButton3.setCallbackData(pisces);

        inlineKeyboardButton4.setText("Aries");
        inlineKeyboardButton4.setCallbackData(aries);

        inlineKeyboardButton5.setText("Virgo");
        inlineKeyboardButton5.setCallbackData(virgo);

        inlineKeyboardButton6.setText("Taurus");
        inlineKeyboardButton6.setCallbackData(taurus);

        inlineKeyboardButton7.setText("Libra");
        inlineKeyboardButton7.setCallbackData(libra);

        inlineKeyboardButton8.setText("Gemini");
        inlineKeyboardButton8.setCallbackData(gemini);

        inlineKeyboardButton9.setText("Cancer");
        inlineKeyboardButton9.setCallbackData(cancer);

        inlineKeyboardButton10.setText("Leo");
        inlineKeyboardButton10.setCallbackData(leo);

        inlineKeyboardButton11.setText("Scorpio");
        inlineKeyboardButton11.setCallbackData(scorpio);

        inlineKeyboardButton12.setText("Sagittarius");
        inlineKeyboardButton12.setCallbackData(sagittarius);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow1.add(inlineKeyboardButton3);

        keyboardButtonsRow2.add(inlineKeyboardButton4);
        keyboardButtonsRow2.add(inlineKeyboardButton5);
        keyboardButtonsRow2.add(inlineKeyboardButton6);

        keyboardButtonsRow3.add(inlineKeyboardButton7);
        keyboardButtonsRow3.add(inlineKeyboardButton8);
        keyboardButtonsRow3.add(inlineKeyboardButton9);


        keyboardButtonsRow4.add(inlineKeyboardButton10);
        keyboardButtonsRow4.add(inlineKeyboardButton11);
        keyboardButtonsRow4.add(inlineKeyboardButton12);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

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
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), "Виберіть ваш знак зодіаку"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {






            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            String answer = "";

//            EditMessageText new_message = new EditMessageText();
//            new_message.setChatId(String.valueOf(chat_id));
//            new_message.setMessageId(toIntExact(message_id));
//            new_message.setText("Leo");
//            execute(new_message);




            if (call_data.equals("leo")) {
                try {
                    answer = "leo";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("capricorn")) {
                try {
                    answer = "capricorn";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("aquarius")) {
                try {
                    answer = "aquarius";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("pisces")) {
                try {
                    answer = "pisces";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("aries")) {
                try {
                    answer = "aries";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("gemini")) {
                try {
                    answer = "gemini";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("cancer")) {
                try {
                    answer = "cancer";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("virgo")) {
                try {
                    answer = "virgo";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("libra")) {
                try {
                    answer = "libra";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("Scorpio")) {
                try {
                    answer = "scorpio";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("sagittarius")) {
                try {
                    answer = "sagittarius";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("taurus")) {
                try {
                    answer = "taurus";
                    execute(backButton(chat_id, answer));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("back")) {
                try {
                    execute(sendInlineKeyBoardMessage(chat_id, "Виберіть ваш знак зодіаку"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }







//            String getSign = update.getCallbackQuery().getData();
//
//            SendMessage message = new SendMessage();
//
//
//             System.out.println("getSign: " + getSign);
//
//             switch (getSign) {
//                case "leo" :  {
//                    try {
//                        message.setText("Leo");
//                        execute(message);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                 case "capricorn" : {
//                    try {
//                        message.setText("You're Capricorn");
//                        execute(message);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//            }

//            if (str.equals(callback_username)) {
//                repo.save(new UserFromTelegram(update.getCallbackQuery().getFrom().getFirstName()));
//            }
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
