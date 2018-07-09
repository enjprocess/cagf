package @ACTIONPACKAGENAME@;

import @IMPORTBEANSNAME@;
import @IMPORTSERVICEINTERFACE@;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateP@CLASSNAME@ extends ActionSupport{

   private long id;

   private @CLASSNAME@ @classname@;
   
   private @CLASSNAME@Service @classname@Service;
   
   public void set@CLASSNAME@Service(@CLASSNAME@Service @classname@Service) {
       this.@classname@Service = @classname@Service;
   }
   
   public @CLASSNAME@Service get@CLASSNAME@Service() {
       return @classname@Service;
   }
   
   public void set@CLASSNAME@(@CLASSNAME@ @classname@) {
       this.@classname@ = @classname@;
   }
   
   public @CLASSNAME@ get@CLASSNAME@() {
       return this.@classname@;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       this.@classname@ = @classname@Service.get@CLASSNAME@(id);
       return "success";
   }
}