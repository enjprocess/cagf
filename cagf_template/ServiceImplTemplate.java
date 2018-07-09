package @PACKAGE@;

import @IMPORTBEANS@;
import @IMPORTDAOINTERFACE@;
import @IMPORTSERVICEINTERFACE@;

import java.util.*;

public class @CLASSNAME@ServiceImpl implements @CLASSNAME@Service{

   private @CLASSNAME@Dao @classname@Dao;
   
   private String LIST_ALL_DESC = "from @CLASSNAME@ bean order by bean.id desc";
   
   private String LIST_ALL_ASC = "from @CLASSNAME@ bean order by bean.id asc";
   
   public @CLASSNAME@Dao get@CLASSNAME@() {
       return @classname@Dao;
   }
   
   public void set@CLASSNAME@Dao(@CLASSNAME@Dao @classname@Dao) {
        this.@classname@Dao = @classname@Dao;
   }   

   public void save@CLASSNAME@(@CLASSNAME@ bean) {
       
       @classname@Dao.save@CLASSNAME@(bean);
   }
   public void delete@CLASSNAME@(Long id) {
       @classname@Dao.remove@CLASSNAME@(id);
   }
   
   public void update@CLASSNAME@(@CLASSNAME@ bean) {
       @classname@Dao.update@CLASSNAME@(bean);
   }
   
   public @CLASSNAME@ get@CLASSNAME@(Long id) {
       return @classname@Dao.get@CLASSNAME@(id);
   }
   
   public long get@CLASSNAME@Count() {
       return @classname@Dao.get@CLASSNAME@Count(LIST_ALL_DESC);
   }
   
   public List<@CLASSNAME@> list@CLASSNAME@(int start, int number) {
       return this.list@CLASSNAME@Desc(start, number);
   }
   
   public List<@CLASSNAME@> list@CLASSNAME@Desc(int start, int number) {
       return @classname@Dao.search@CLASSNAME@s(LIST_ALL_DESC, new String[]{}, start, number);
   }
   
   public List<@CLASSNAME@> list@CLASSNAME@Asc(int start, int number) {
       return @classname@Dao.search@CLASSNAME@s(LIST_ALL_ASC, new String[]{}, start, number);
   }
   
   
	
	
	
}