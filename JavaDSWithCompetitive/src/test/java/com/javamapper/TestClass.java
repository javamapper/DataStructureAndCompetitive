package com.javamapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
         String R = br.readLine();
         int N = Integer.parseInt(br.readLine().trim());
         String[] P = new String[N];
         for(int i_P = 0; i_P < N; i_P++)
         {
         	P[i_P] = br.readLine();
         }

         String[] out_ = solve(R, N, P);
         for (int i_out_ = 0; i_out_ < out_.length; i_out_++)
         {
         	System.out.println(out_[i_out_]);
         }

         wr.close();
         br.close();
    }
    static String[] solve(String R, int N, String[] P){
        try{
       DateFormat df=new SimpleDateFormat("hh:mm:ss");
       Date d=df.parse(R);
       for(int i=0;i<N;i++){
           Date temp=df.parse(P[i]);
           P[i]=toDuration(TimeUnit.MILLISECONDS.toMillis(d.getTime()-temp.getTime()),d.getTime());
       }
       }catch(Exception e){

       }
       return P;
    
    }
    public static final List<Long> times=Arrays.asList(
        TimeUnit.DAYS.toMillis(365),
        TimeUnit.DAYS.toMillis(30),
        TimeUnit.DAYS.toMillis(1),
        TimeUnit.HOURS.toMillis(1),
        TimeUnit.MINUTES.toMillis(1),
        TimeUnit.SECONDS.toMillis(1)
    );
    public static final List<String> timesString=Arrays.asList("year","month","day","hour","minute", "second");
    public static String toDuration(long t,long r){
        StringBuffer res=new StringBuffer();
        for(int i=0;i<times.size();i++){
            Long current = times.get(i);
            long temp=t/current;
            if(temp>0){
                res.append(temp).append(" ").append(timesString.get(i)).append(temp!=1?"s":"").append(" ago");
                break;
            }
        }
        if("".equals(res.toString())){
            return "now";
        }
        return res.toString();
    }
}