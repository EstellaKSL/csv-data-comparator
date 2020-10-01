package com.bos.training;


// use to validate fields in files
public class Client {

    private  String customerId;
    private  String accountNo;
    private  String currency;
    private  String accountType;
    private  Integer balance;

    public Client(String customerId, String accountNo, String currency, String accountType, Integer balance) {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.currency = currency;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getCustomerId(){
        return  customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNo(){
        return  accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getCurrency(){
        return  currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountType(){
        return  accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getBalance(){
        return  balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return ""+customerId+","+accountNo+","+currency+","+accountType+","+balance+"";
    }
}

