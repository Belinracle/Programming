package Connection;

import java.io.Serializable;

/**
 * Класс которыми Клиент общается с Сервером
 */
public class TransferObject implements Serializable {
    Object obj;
    String request;
    public TransferObject(String requestString){
        request=requestString;
    }
    public String getRequest(){
        return request;
    }
    public Object getObject(){
        return obj;
    }
    public void putObject(Object obj){
        this.obj=obj;
    }
}
