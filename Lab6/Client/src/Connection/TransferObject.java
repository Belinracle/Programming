package Connection;

public class TransferObject {
    Object obj;
    String request;
    TransferObject(String requestString){
        request=requestString;
    }
    String getRequest(){
        return request;
    }
    Object getObject(){
        return obj;
    }
    void putObject(Object obj){
        this.obj=obj;
    }
}
