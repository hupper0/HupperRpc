package com.nettytest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:38
 */
public enum Action {
    PING("ping"),
    EXECUTE("execute"),
    KILL("kill"),
    PATH("path"),
    MONITOR("monitor"),
    UPGRADE("upgrade"),
    RUNNING_TASK("runningTask  获得agent正在执行任务的列表"),
    REPORT("任务执行完毕, 报告任务状态给服务端 taskScheduler"),
    ACK_TASK_RESPONSE("服务端确认收到任务回调信息, 删除缓存文件"),
    REGISTER("agent 发送mac，password等信息到server进行注册"),
    TASK_EXECUTE_MSG("任务执行时实时上报输出执行日志"),
    REGISTER_RESPONSE("agent 注册回复"),
    ERROR("输出错误／异常信息"),
    PUBLISH("发布workflow");

    static Map<String, Action> nameMap = new HashMap<>();

    static {
        for (Action action : Action.values()) {
            nameMap.put(action.name().toLowerCase(), action);
        }
    }

    String description;


    Action(String description) {
        this.description = description;
    }

    public static Action get(String name) {
        return nameMap.get(name.toLowerCase());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
