package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum OperationSystemVariants {

    FREE("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)","select_option_83"),
    PAID_UBUNTU_PRO("Paid: Ubuntu Pro", "select_option_84");

    private String operationSystemTextValue;
    private String operationSystemOptionId;

    OperationSystemVariants(String operationSystemTextValue, String operationSystemOptionId) {
        this.operationSystemTextValue = operationSystemTextValue;
        this.operationSystemOptionId = operationSystemOptionId;
    }

    public String getOperationSystemTextValue() {
        return operationSystemTextValue;
    }

    public void setOperationSystemTextValue(String operationSystemTextValue) {
        this.operationSystemTextValue = operationSystemTextValue;
    }

    public String getOperationSystemOptionId() {
        return operationSystemOptionId;
    }

    public void setOperationSystemOptionId(String operationSystemOptionId) {
        this.operationSystemOptionId = operationSystemOptionId;
    }
}
