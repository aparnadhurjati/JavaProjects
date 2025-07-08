package com.aparna.java.hello_springai.toolcalling;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;

import com.aparna.java.hello_springai.functioncalling.wallet.Share;
import com.aparna.java.hello_springai.functioncalling.wallet.WalletRepository;

public class WalletTools {
    private WalletRepository walletRepository;

    public WalletTools(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Tool(description = "Number of shares for each company in my wallet")
    public List<Share> getNumberOfShares() {
        return (List<Share>) walletRepository.findAll();
    }
}
