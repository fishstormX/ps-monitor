package cn.fishmaple.psMonitor.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectReflectUtil {

   protected static Method getMethod(Class clazz, Field field){
       Class[] parameterTypes = new Class[1];
       parameterTypes[0] = field.getType();
       StringBuffer sb = new StringBuffer();
       sb.append("set");
       sb.append(field.getName().substring(0, 1).toUpperCase());
       sb.append(field.getName().substring(1));
       try {
           Method method = clazz.getMethod(sb.toString(), parameterTypes);
           return method;
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       }
       return null;
   }

   public static int setParam(Object object,Field field,Object goal){
       Method method = getMethod(object.getClass(),field);
       try {
           method.invoke(object, goal);
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
       return 1;
   }
}
