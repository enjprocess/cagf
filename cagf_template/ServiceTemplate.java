package @PACKAGE@;

import @IMPORTBEANS@;

import java.util.*;

public interface @CLASSNAME@Service {

   void save@CLASSNAME@(@CLASSNAME@ bean);
   
   void delete@CLASSNAME@(Long id);
   
   void update@CLASSNAME@(@CLASSNAME@ bean);
   
   @CLASSNAME@ get@CLASSNAME@(Long id);
   
   long get@CLASSNAME@Count();
   
   List<@CLASSNAME@> list@CLASSNAME@(int start, int number);
   
   List<@CLASSNAME@> list@CLASSNAME@Desc(int start, int number);
   
   List<@CLASSNAME@> list@CLASSNAME@Asc(int start, int number);
	
	
	
}