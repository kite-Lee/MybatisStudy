package com.lee.demo01;

public class Client {

    public static void main(String[] args) {

        Host host = new Host();
//        host.rent();
        // 代理
        Proxy proxy = new Proxy(host);

        proxy.rent();
        proxy.fare();
        proxy.seeHouse();
        proxy.signContract();

    }
}
