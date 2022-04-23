package com.example.SportDesk.domain.util;

import com.example.SportDesk.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}