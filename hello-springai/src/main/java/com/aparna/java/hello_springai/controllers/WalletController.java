package com.aparna.java.hello_springai.controllers;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aparna.java.hello_springai.toolcalling.StockTools;
import com.aparna.java.hello_springai.toolcalling.WalletTools;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final ChatClient chatClient;
    private final StockTools stockTools;
    private final WalletTools walletTools;

    public WalletController(ChatClient.Builder chatClientBuilder,
                            StockTools stockTools,
                            WalletTools walletTools) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        this.stockTools = stockTools;
        this.walletTools = walletTools;
    }

    /** Sample request: http://localhost:8080/wallet/with-tools
     * 
     * Sample response:
     * Here are the current stock prices and the total value of your wallet based on the latest prices: - **AAPL**: 100 shares at $209.94 each = $20,994.00 - **AMZN**: 300 shares at $223.48 each = $67,044.00 - **META**: 300 shares at $718.64 each = $215,592.00 - **MSFT**: 400 shares at $497.83 each = $199,132.00 - **NVDA**: 200 shares at $158.24 each = $31,648.00 ### Total Wallet Value: - **Total** = $20,994.00 + $67,044.00 + $215,592.00 + $199,132.00 + $31,648.00 = **$534,410.00** Your wallet is currently valued at **$534,410.00**.
     */
    @GetMapping("/with-tools")
    String calculateWalletValueWithTools() {
        PromptTemplate pt = new PromptTemplate("""
        What’s the current value in dollars of my wallet based on the latest stock daily prices ?
        """);

        return this.chatClient.prompt(pt.create())
                .tools(stockTools, walletTools)
                .call()
                .content();
    }

    @GetMapping("/highest-day/{days}")
    String calculateHighestWalletValue(@PathVariable int days) {
        PromptTemplate pt = new PromptTemplate("""
        On which day during last {days} days my wallet had the highest value in dollars based on the historical daily stock prices ?
        """);

        return this.chatClient.prompt(pt.create(Map.of("days", days)))
                .tools(stockTools, walletTools)
                .call()
                .content();
    }
}
