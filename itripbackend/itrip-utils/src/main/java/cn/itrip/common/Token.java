package cn.itrip.common;

 public class Token {
    private  String token;

     public String getToken() {
         return token;
     }

     public void setToken(String token) {
         this.token = token;
     }

     public Long getCreaDate() {
         return creaDate;
     }

     public void setCreaDate(Long creaDate) {
         this.creaDate = creaDate;
     }

     public Long getGqDate() {
         return gqDate;
     }

     public void setGqDate(Long gqDate) {
         this.gqDate = gqDate;
     }

     private Long creaDate;
    private Long gqDate;




}
