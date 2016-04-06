package me.edagarli.TimerMonitor.util;

import java.util.LinkedList;

public class QuestInfo
{
    private int level = 0;
    private long startTime;
    private LinkedList<MethodInfo> linkedList;

    public QuestInfo(LinkedList<MethodInfo> linkedList)
    {
        this.linkedList = linkedList;
    }

    public LinkedList<MethodInfo> getLinkedList() {
        return this.linkedList;
    }

    public void setLinkedList(LinkedList<MethodInfo> linkedList) {
        this.linkedList = linkedList;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel() {
        this.level += 1;
    }

    public void decreaseLevel() {
        this.level -= 1;
    }

    public String toString()
    {
        try
        {
            return printTree(this.linkedList, 0, this.linkedList.size() - 1);
        } catch (Exception e) {
        }
        return "MethodInfo.toString() Error! ";
    }

    private String getAappendString(boolean isLeaf, MethodInfo methodInfo)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < methodInfo.getLevel(); i++)
        {
            stringBuilder.append("    ");
        }
        if (isLeaf)
        {
            stringBuilder.append("+---[");
        }
        else stringBuilder.append("---+[");

        stringBuilder.append((methodInfo.getStartTime().longValue() - getStartTime()) / 1000000L).append("-");
        stringBuilder.append((methodInfo.getEndTime().longValue() - getStartTime()) / 1000000L).append("]");
        stringBuilder.append((methodInfo.getEndTime().longValue() - methodInfo.getStartTime().longValue()) / 1000000L).append("ms ");
        stringBuilder.append(methodInfo.getMethodName()).append("\n");
        return stringBuilder.toString();
    }

    private String printTree(LinkedList<MethodInfo> linkedList, int startIndex, int endIndex)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int headLevel = ((MethodInfo)linkedList.get(endIndex)).getLevel();
        if (startIndex == endIndex) {
            stringBuilder.append(getAappendString(true, (MethodInfo)linkedList.get(endIndex)));
            return stringBuilder.toString();
        }

        stringBuilder.append(getAappendString(false, (MethodInfo)linkedList.get(endIndex)));

        int nextHeadLevel = headLevel + 1;
        int tempStartIndex = startIndex;
        for (int i = startIndex; i < endIndex; i++)
        {
            if (((MethodInfo)linkedList.get(i)).getLevel() == nextHeadLevel)
            {
                stringBuilder.append(printTree(linkedList, tempStartIndex, i));
                tempStartIndex = i + 1;
            }
        }
        return stringBuilder.toString();
    }
}