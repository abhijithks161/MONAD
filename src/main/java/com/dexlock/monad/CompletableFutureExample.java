package com.dexlock.monad;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void checkUrlString(final String url) {
        Helper helper = new Helper();

        CompletableFuture<Boolean> validateUrl = CompletableFuture.supplyAsync(() -> {
            if ((helper.validateUrl(url))) {
                return true;
            }
            throw new RuntimeException("Invalid URL");
        }).exceptionally(throwable -> {
            System.out.println(throwable);
            return null;
        });

        CompletableFuture<String> sendGetUrl = validateUrl.thenApplyAsync(Boolean -> {
            try {
                String result = helper.getUrl(url);
                helper.close();
                return result;
            } catch (Exception e) {
                throw new RuntimeException("URL doesn't exist");
            }
        }).exceptionally(throwable -> {
            System.out.println(throwable);
            return null;
        });

        CompletableFuture<Boolean> printUrl = sendGetUrl.thenApply(urlBody -> {
            System.out.println(urlBody);
            return true;
        }).exceptionally(throwable -> {
            System.out.println("No URL Body");
            return false;
        });

        printUrl.join();
    }
}
