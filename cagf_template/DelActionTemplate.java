package @ACTIONPACKAGENAME@;

import @IMPORTBEANSNAME@;
import @IMPORTSERVICEINTERFACE@;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class Del@CLASSNAME@ extends ActionSupport{

   private long id;

   private @CLASSNAME@Service @classname@Service;
   
   public void set@CLASSNAME@Service(@CLASSNAME@Service @classname@Service) {
       this.@classname@Service = @classname@Service;
   }
   
   public @CLASSNAME@Service get@CLASSNAME@Service() {
       return @classname@Service;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       @classname@Service.delete@CLASSNAME@(id);
       return "success";
   }
}