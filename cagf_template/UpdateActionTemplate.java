package @ACTIONPACKAGENAME@;

import @IMPORTBEANSNAME@;
import @IMPORTSERVICEINTERFACE@;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class Update@CLASSNAME@ extends ActionSupport{
   
    private @CLASSNAME@Service @classname@Service;
   
@DECLAREPROPERTIES@
   
    public void set@CLASSNAME@Service(@CLASSNAME@Service @classname@Service) {
        this.@classname@Service = @classname@Service;
    }
   
    public @CLASSNAME@Service get@CLASSNAME@Service() {
        return @classname@Service;
    }

    public String execute() {
        
        @CLASSNAME@ bean = @classname@Service.get@CLASSNAME@(id);
        
@PREPAREDATA@
   
        @classname@Service.update@CLASSNAME@(bean);
        return "success";
   }
   
@GETTER/SETTER@
}