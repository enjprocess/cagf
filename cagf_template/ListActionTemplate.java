package @ACTIONPACKAGENAME@;

import @IMPORTBEANSNAME@;
import @IMPORTSERVICEINTERFACE@;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class List@CLASSNAME@ extends ActionSupport{
   
   private @CLASSNAME@Service @classname@Service;
   
   private List<@CLASSNAME@> @classname@List;
   
   public void set@CLASSNAME@Service(@CLASSNAME@Service @classname@Service) {
       this.@classname@Service = @classname@Service;
   }
   
   public @CLASSNAME@Service get@CLASSNAME@Service() {
       return @classname@Service;
   }
   
   public void set@CLASSNAME@List(List<@CLASSNAME@> @classname@List) {
       this.@classname@List = @classname@List;
   }
   
   public List<@CLASSNAME@> get@CLASSNAME@List() {
       return this.@classname@List;
   }

   public String execute() {
        
       @classname@List = @classname@Service.list@CLASSNAME@(0, 10);
       return "success";
   }
}