package me.edagarli.TimerMonitor.monitor;

import me.edagarli.TimerMonitor.util.MethodInfo;
import me.edagarli.TimerMonitor.util.QuestInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;


/**
 * Created by zongzi on 2016/3/10.
 *
 *
 * 输出格式为
 * A[0-50]50ms
 *---B[20-30]30ms
 *-----C[25-28]3ms
 *
 */
public class MonitorAround {
//    Logger logger = LoggerFactory.getLogger(MonitorAround.class);
    Logger loggerMonitor = LoggerFactory.getLogger(MonitorAround.class);//打印超时的方法栈的日志

    private int maxTime = 300;
    private ThreadLocal<QuestInfo> myThreadLocal = new ThreadLocal();

    public Object watchPerformance(ProceedingJoinPoint joinpoint) throws Throwable {
        Object result = null;
        try {
            QuestInfo questInfo = myThreadLocal.get();
            if (questInfo == null) {
                questInfo = new QuestInfo(new LinkedList<MethodInfo>());
                questInfo.setStartTime(System.nanoTime());
                myThreadLocal.set(questInfo);
            }

            // 第一层方法进来是否要清空LocalThread 效率问题 不清空也没事
            // 下次该线程有方法走完也会被清空

            questInfo.increaseLevel();
            long startTime = System.nanoTime();
            /*run method*/
            result = joinpoint.proceed();
            /*End run method*/
            long endTime = System.nanoTime();
            questInfo.decreaseLevel();
            MethodInfo methodInfo=new MethodInfo(
                    questInfo.getLevel(),/*层次*/
                    joinpoint.getSignature().toString(),/*方法名字*/
                    startTime,/*开始时间*/
                    endTime);/*结束时间*/

            questInfo.getLinkedList().add(methodInfo);//方法多叉树的后序遍历的顺序
            if (questInfo.getLevel() == 0) {//最外层判断是否超过最大时间值
                myThreadLocal.remove();//清理
//                if (( endTime-startTime) > maxTime*1000000) {//我听说除法效率比乘法效率低- -、
                    //打印 questInfo
                    StringBuilder sbOut=new StringBuilder("TimeMonitor Warn:TimeOut:\n");
                    sbOut.append(questInfo.toString());
                    loggerMonitor.warn(sbOut.toString());
//                }
            }
        } catch (Throwable e) {
            //不记录异常信息  否则日志不方便阅读  抛出
//            StringBuilder sbErrorOut=new StringBuilder();
//            sbErrorOut.append("\nTimeMonitor Error:Method stack Info:\n");
//            sbErrorOut.append(joinpoint.getSignature().toString()).append("\n");
//            sbErrorOut.append("TimeMonitor Error:Method time Info:\n");
//            sbErrorOut.append(myThreadLocal.get().toString()).append("\n");
//            loggerMonitor.error(sbErrorOut.toString());
//            loggerMonitor.error("TimeMonitor Error:Error Info:");
//            loggerMonitor.error(e.getMessage(), e);
            myThreadLocal.remove();//清空本地缓存
            throw e;//抛出异常
        }
        return result;
    }


    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTimer) {
        this.maxTime = maxTimer;
    }
}
