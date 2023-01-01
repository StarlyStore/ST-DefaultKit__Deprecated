package net.starly.defaultkit.data;

import net.starly.core.data.Config;
import net.starly.defaultkit.DefaultKit;

public class StringData {
    private Config message = new Config("config", DefaultKit.getPlugin());


    public String msgGetDefaultKit() {
        return getPrefix() + message.getString("messages.getDefaultKit");
    }

    public String msgCompleteResetKit() {
        return getPrefix() + message.getString("messages.completeResetKit");
    }

    public String errMsgInventoryFull() {
        return getPrefix() + message.getString("errMessages.inventoryFull");
    }

    public String errMsgNoFindPlayer() {
        return getPrefix() + message.getString("errMessages.noFindPlayer");
    }

    public String errMsgAlreadyGetDefaultKit() {
        return getPrefix() + message.getString("errMessages.alreadyGetDefaultKit");
    }

    public String errMsgNoPermission() {
        return getPrefix() + message.getString("errMessages.noPermission");
    }

    public String errMsgInvalidCommand() {
        return getPrefix() + message.getString("errMessages.invalidCommand");
    }


    public String getPrefix() {
        return message.getString("prefix");
    }
}
