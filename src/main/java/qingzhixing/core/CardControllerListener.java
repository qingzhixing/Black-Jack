package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

public class CardControllerListener {
    private SendHook sendHook;
    private InitializeHook initializeHook;

    public CardControllerListener() {
        this.sendHook = new SendHook() {
            @Override
            public void OnCardSend(Card card) {
            }
        };
        this.initializeHook = new InitializeHook() {
            @Override
            public void OnCardInitialize() {
            }
        };
    }

    public CardControllerListener(SendHook sendHook, InitializeHook initializeHook) {
        this.sendHook = sendHook;
        this.initializeHook = initializeHook;
    }

    public void SetSendHook(@NotNull SendHook sendHook) {
        this.sendHook = sendHook;
    }

    public void SetInitializeHook(@NotNull InitializeHook initializeHook) {
        this.initializeHook = initializeHook;
    }

    public void OnCardSend(Card send) {
        sendHook.OnCardSend(send);
    }

    public void OnCardInitialize() {
        initializeHook.OnCardInitialize();
    }

    @FunctionalInterface
    public interface SendHook {
        void OnCardSend(Card send);
    }

    @FunctionalInterface
    public interface InitializeHook {
        void OnCardInitialize();
    }
}