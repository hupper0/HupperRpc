package com.nettytest;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:36
 */
public enum MessageField {

    WORKFLOW_ID(""),
    TASK_ID(""),
    SHELL(""),
    EXCLUSIVE(""),
    PID("执行Task 要发送给agent"),
    MONITOR_PORT("返回对机器监控的端口"),
    STATUS("Task执行完的状态, value 取 TaskStatus"),
    TASK_EXECUTE_MSG("任务执行完毕之后的日志信息"),
    EXIT_CODE("任务执行完毕后退出编码"),
    RUNNING_TASK("返回正在运行的Task 列表, value: List<taskId[String]>"),
    IP("注册发送的ip"),
    PORT("注册发送的port"),
    REGISTER_RESPONSE(""),
    PATH("PATH"),
    UPGRADE_VERSION("server发送升级版本给agent进行升级"),
    VERSION("Agent 版本信息"),
    MONITOR("监控结果"),
    ERROR_CODE("异常／错误码"),
    ERROR_MESSAGE("异常／错误信息"),
    HEARTBEAT_DELAY("配置心跳间隔");

    private String description;

    MessageField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name();
    }
}
