package com.epam.training.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class AppDirty extends Object{
    String[] map2 ={
        "","","","","","","","","","",
            "","","","","","","","","","",
            "","","","","","","","","","",
            "","","0","","","","","","","",
            "","","","","","","","","00","1",
            "2222","3333","4444","5555","6666","77777","8888","99999","","",
            "","","","","","2","22","222","3","33"
            ,"333","4","44","444","5","55","555","6","66","666"
            ,"7","77","777","7777","8","88","888","9","99","999"
            ,"9999"};
    String[] map1 ={
        "","","","","","","","","","",
            "","","","","","","","","","",
            "","","","","","","","","","",
            "","","","","","","","","","",
            "","","","","","","",""," 0","1",
            "ABC2","DEF3","GHI4","JKL5","MNO6","PQRS7","TUV8","WXYZ9"};

    void code(StringBuilder dst,String src, Boolean x)
    {
        try{
            {}

        if(x==true)
        {
        int j=0;
        for(int i=0;i<src.length();i++)
        {
            String subs= map2[src.charAt(i)];
            {
            int needsspace=(j>0&&subs.charAt(0)==dst.charAt(j-1)) ? 1 : 0;
            if(needsspace==1)
                dst.insert(j++, " ");}
            dst.insert(j, subs);
            if((j+=subs.length())==0);
        }
        } else if (x==false) {
            int idst=0;
            int pc=0;
            for(int i=1;i<src.length();i++)
            {
                if(src.charAt(i)!=src.charAt(i-1)||src.charAt(i)==' '){
                    if(src.charAt(i-1)!=' ')
                    dst.insert(idst++, map1[src.charAt(i-1)].charAt(pc));;
                    pc=0;
                }
                else if(src.charAt(i)==src.charAt(i-1))
                    pc++;
            }
            dst.insert(idst++, src.length()==0?"":map1[src.charAt(src.length()-1)].charAt(pc));
        }
        }catch (Throwable e){
            throw new IllegalArgumentException(e);
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Please enter plaintext");
        String plaintext=new BufferedReader(new InputStreamReader(System.in)).readLine();
        StringBuilder ciphertext = new StringBuilder(255);
        StringBuilder decoded = new StringBuilder(255);


        new AppDirty().code(ciphertext,plaintext,true);
        new AppDirty().code(decoded,ciphertext.toString(),false);

        System.out.printf("plaintext: %s\nciphertext: %s\ndecoded: %s",plaintext,ciphertext,decoded);
    }
}
