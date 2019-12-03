package bai.yun.umeng.excaption;

/**
 * 自定义异常
 */
public class CustomException extends Exception {

    private String code = "";
    private String msg = "";

    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public String toString() {
        return "CustomException{code='" + code + ", msg='" + msg + '}';
    }
}