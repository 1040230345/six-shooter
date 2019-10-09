package com.my_util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
@Component
public class Encryption {
    //自定p和q,p和q为互素
    private static int p=53,q=61;
    //n=p*q
    private static BigInteger n=new BigInteger(""+p*q);
    //gcd(e,fn)=1   fn=(p-1)(q-1)=3120
    private static BigInteger e=new BigInteger("17");
    //通过e*d===1(mod fn)计算d的值
    private static BigInteger d=new BigInteger("2753");

    //加密(公钥)
    public String Input(String input){
        String s="";
        BigInteger x;
        //遍历input字符串
        for(int i=0;i<input.length();i++){
            x=new BigInteger((int)input.charAt(i)+"");
            int lk;
            char c=(char)Integer.parseInt(x.modPow(e,n).toString());
            s=s+c;
        }
        return s;
    }
    //解密(私钥)
    public String Out(String out){
        String s="";
        BigInteger x;
        //遍历out字符串
        for(int i=0;i<out.length();i++){
            x=new BigInteger((int)out.charAt(i)+"");
            char c=(char)Integer.parseInt(x.modPow(d,n).toString());
            s=s+c;
        }
        return s;
    }
}
