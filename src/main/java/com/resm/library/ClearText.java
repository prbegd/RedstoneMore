package com.resm.library;

import net.minecraft.entity.decoration.DisplayEntity;
public class ClearText extends Thread {
    private final DisplayEntity.TextDisplayEntity entity;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        entity.discard();
    }

    public ClearText(DisplayEntity.TextDisplayEntity entity) {
        this.entity = entity;
    }
}