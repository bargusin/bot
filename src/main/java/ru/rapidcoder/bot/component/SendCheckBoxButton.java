package ru.rapidcoder.bot.component;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SendCheckBoxButton extends InlineKeyboardButton implements Component {

    private final static String BUTTON_TEXT = "Send";

    private final List<ChekBoxButton> checkBoxButtons;

    public SendCheckBoxButton(String callbackData, List<ChekBoxButton> checkBoxButtons) {
        setText(BUTTON_TEXT);
        setCallbackData(callbackData);
        this.checkBoxButtons = checkBoxButtons;
    }

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        for (ChekBoxButton button : checkBoxButtons) {
            result.append(button.getCallbackData()).append(":").append(button.isSelected()).append("\n");
        }
        return StringUtils.isEmpty(result.toString()) ? null : result.toString();
    }
}
