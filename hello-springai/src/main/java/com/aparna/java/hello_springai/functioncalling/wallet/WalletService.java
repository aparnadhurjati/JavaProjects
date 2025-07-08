// package com.aparna.java.hello_springai.functioncalling.wallet;

// import java.util.function.Supplier;

// import java.util.List;

// public class WalletService implements Supplier<WalletResponse> {

//     private WalletRepository walletRepository;

//     public WalletService(WalletRepository walletRepository) {
//         this.walletRepository = walletRepository;
//     }

//     @Override
//     public WalletResponse get() {
//         return new WalletResponse((List<Share>) walletRepository.findAll());
//     }


// }
