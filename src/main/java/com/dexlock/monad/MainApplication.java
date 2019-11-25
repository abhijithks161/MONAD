package com.dexlock.monad;

import java.util.concurrent.CompletableFuture;

public class MainApplication {
    public static void main(String[] args) {
        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        String url = "https://facebook.com";
        completableFutureExample.checkUrlString(url);
    }
}