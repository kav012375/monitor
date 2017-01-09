package com.wulin.dal.task.constants;

/**
 * Created by zeusw on 2016/12/20.
 */
public enum StatusEnum {
    NotRun(0),
    HasRun(1),
    RunFinish(2),
    RunFailed(3),
    OtherError(4)
    ;

    private int code;
    private StatusEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
