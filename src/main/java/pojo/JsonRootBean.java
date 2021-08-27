/**
  * Copyright 2021 json.cn 
  */
package pojo;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2021-08-19 23:32:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private String owner;
    private String tableName;
    private String operationType;
    private String columnNum;
    private String scn;
    private String seqid;
    private String tranid;
    private String opTs;
    private String loaderTime;
    private String rowid;
    private List<AfterColumnList> afterColumnList;
    private String ognTopic;
    private String ognPartition;
    private String ognOffset;
    private String sysCode;
    private String dmgrProcessStartTime;
    private String dmgrProcessEndTime;
    private String pkNames;
    private String pkValues;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(String columnNum) {
        this.columnNum = columnNum;
    }

    public String getScn() {
        return scn;
    }

    public void setScn(String scn) {
        this.scn = scn;
    }

    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getOpTs() {
        return opTs;
    }

    public void setOpTs(String opTs) {
        this.opTs = opTs;
    }

    public String getLoaderTime() {
        return loaderTime;
    }

    public void setLoaderTime(String loaderTime) {
        this.loaderTime = loaderTime;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public List<AfterColumnList> getAfterColumnList() {
        return afterColumnList;
    }

    public void setAfterColumnList(List<AfterColumnList> afterColumnList) {
        this.afterColumnList = afterColumnList;
    }

    public String getOgnTopic() {
        return ognTopic;
    }

    public void setOgnTopic(String ognTopic) {
        this.ognTopic = ognTopic;
    }

    public String getOgnPartition() {
        return ognPartition;
    }

    public void setOgnPartition(String ognPartition) {
        this.ognPartition = ognPartition;
    }

    public String getOgnOffset() {
        return ognOffset;
    }

    public void setOgnOffset(String ognOffset) {
        this.ognOffset = ognOffset;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getDmgrProcessStartTime() {
        return dmgrProcessStartTime;
    }

    public void setDmgrProcessStartTime(String dmgrProcessStartTime) {
        this.dmgrProcessStartTime = dmgrProcessStartTime;
    }

    public String getDmgrProcessEndTime() {
        return dmgrProcessEndTime;
    }

    public void setDmgrProcessEndTime(String dmgrProcessEndTime) {
        this.dmgrProcessEndTime = dmgrProcessEndTime;
    }

    public String getPkNames() {
        return pkNames;
    }

    public void setPkNames(String pkNames) {
        this.pkNames = pkNames;
    }

    public String getPkValues() {
        return pkValues;
    }

    public void setPkValues(String pkValues) {
        this.pkValues = pkValues;
    }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "owner='" + owner + '\'' +
                ", tableName='" + tableName + '\'' +
                ", operationType='" + operationType + '\'' +
                ", columnNum='" + columnNum + '\'' +
                ", scn='" + scn + '\'' +
                ", seqid='" + seqid + '\'' +
                ", tranid='" + tranid + '\'' +
                ", opTs='" + opTs + '\'' +
                ", loaderTime='" + loaderTime + '\'' +
                ", rowid='" + rowid + '\'' +
                ", afterColumnList=" + afterColumnList +
                ", ognTopic='" + ognTopic + '\'' +
                ", ognPartition='" + ognPartition + '\'' +
                ", ognOffset='" + ognOffset + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", dmgrProcessStartTime='" + dmgrProcessStartTime + '\'' +
                ", dmgrProcessEndTime='" + dmgrProcessEndTime + '\'' +
                ", pkNames='" + pkNames + '\'' +
                ", pkValues='" + pkValues + '\'' +
                '}';
    }
}