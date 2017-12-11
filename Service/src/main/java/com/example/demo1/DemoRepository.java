package com.example.demo1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemoRepository extends JpaRepository<Demo,Integer> {


   public Demo findByEmail(String email);

   @Modifying
   @Query("update Demo o set o.cn= o.cn + :cn where o.email=:email")
   public void updatecn(@Param("email") String email, @Param("cn")String cn);

   @Modifying
   @Query("update Demo o set o.cn = :cn,o.btc= o.btc + :btc where o.email=:email")
   public void updatebtc(@Param("email") String email, @Param("cn")String cn,@Param("btc")String btc);

   @Modifying
   @Query("update Demo o set o.cn = o.cn+ :cn where o.email=:email")
   public void addcn(@Param("email") String email,@Param("cn") String cn);

   @Modifying
   @Query("update Demo o set o.cn = o.cn- :cn where o.email=:email")
   public void subtractcn(@Param("email") String email,@Param("cn") String cn);

   @Modifying
   @Query("update Demo o set o.btc = o.btc+ :btc where o.email=:email")
   public void addbtc(@Param("email") String email,@Param("btc") String btc);

   @Modifying
   @Query("update Demo o set o.btc = o.btc- :btc where o.email=:email")
   public void subtractbtc(@Param("email") String email,@Param("btc") String btc);
}

