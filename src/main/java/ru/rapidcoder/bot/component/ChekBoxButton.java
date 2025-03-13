package ru.rapidcoder.bot.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class ChekBoxButton extends InlineKeyboardButton implements Component {

    private boolean isSelected;

    private final String checked;

    private final String unchecked;

    public ChekBoxButton(String unchecked, String checked, String callbackData) {
        setText(unchecked);
        setCallbackData(callbackData);

        this.checked = checked;
        this.unchecked = unchecked;

        refresh();
    }

    public void execute() {
        if(isSelected) {
            setText(unchecked);
            isSelected = false;
        } else {
            setText(checked);
            isSelected = true;
        }
    }

    public void refresh() {
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
