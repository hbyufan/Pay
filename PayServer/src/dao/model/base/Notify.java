package dao.model.base;

import java.io.Serializable;
import java.util.Date;

public class Notify implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.notify_id
     *
     * @mbg.generated
     */
    private String notifyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.order_record_id
     *
     * @mbg.generated
     */
    private String orderRecordId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.notify_create_time
     *
     * @mbg.generated
     */
    private Date notifyCreateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.notify_expire_time
     *
     * @mbg.generated
     */
    private Date notifyExpireTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.notify_type
     *
     * @mbg.generated
     */
    private Byte notifyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.app_id
     *
     * @mbg.generated
     */
    private String appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notify.notify_result
     *
     * @mbg.generated
     */
    private String notifyResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notify
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.notify_id
     *
     * @return the value of notify.notify_id
     *
     * @mbg.generated
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.notify_id
     *
     * @param notifyId the value for notify.notify_id
     *
     * @mbg.generated
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId == null ? null : notifyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.order_record_id
     *
     * @return the value of notify.order_record_id
     *
     * @mbg.generated
     */
    public String getOrderRecordId() {
        return orderRecordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.order_record_id
     *
     * @param orderRecordId the value for notify.order_record_id
     *
     * @mbg.generated
     */
    public void setOrderRecordId(String orderRecordId) {
        this.orderRecordId = orderRecordId == null ? null : orderRecordId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.notify_create_time
     *
     * @return the value of notify.notify_create_time
     *
     * @mbg.generated
     */
    public Date getNotifyCreateTime() {
        return notifyCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.notify_create_time
     *
     * @param notifyCreateTime the value for notify.notify_create_time
     *
     * @mbg.generated
     */
    public void setNotifyCreateTime(Date notifyCreateTime) {
        this.notifyCreateTime = notifyCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.notify_expire_time
     *
     * @return the value of notify.notify_expire_time
     *
     * @mbg.generated
     */
    public Date getNotifyExpireTime() {
        return notifyExpireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.notify_expire_time
     *
     * @param notifyExpireTime the value for notify.notify_expire_time
     *
     * @mbg.generated
     */
    public void setNotifyExpireTime(Date notifyExpireTime) {
        this.notifyExpireTime = notifyExpireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.notify_type
     *
     * @return the value of notify.notify_type
     *
     * @mbg.generated
     */
    public Byte getNotifyType() {
        return notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.notify_type
     *
     * @param notifyType the value for notify.notify_type
     *
     * @mbg.generated
     */
    public void setNotifyType(Byte notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.app_id
     *
     * @return the value of notify.app_id
     *
     * @mbg.generated
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.app_id
     *
     * @param appId the value for notify.app_id
     *
     * @mbg.generated
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notify.notify_result
     *
     * @return the value of notify.notify_result
     *
     * @mbg.generated
     */
    public String getNotifyResult() {
        return notifyResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notify.notify_result
     *
     * @param notifyResult the value for notify.notify_result
     *
     * @mbg.generated
     */
    public void setNotifyResult(String notifyResult) {
        this.notifyResult = notifyResult == null ? null : notifyResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notify
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Notify other = (Notify) that;
        return (this.getNotifyId() == null ? other.getNotifyId() == null : this.getNotifyId().equals(other.getNotifyId()))
            && (this.getOrderRecordId() == null ? other.getOrderRecordId() == null : this.getOrderRecordId().equals(other.getOrderRecordId()))
            && (this.getNotifyCreateTime() == null ? other.getNotifyCreateTime() == null : this.getNotifyCreateTime().equals(other.getNotifyCreateTime()))
            && (this.getNotifyExpireTime() == null ? other.getNotifyExpireTime() == null : this.getNotifyExpireTime().equals(other.getNotifyExpireTime()))
            && (this.getNotifyType() == null ? other.getNotifyType() == null : this.getNotifyType().equals(other.getNotifyType()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getNotifyResult() == null ? other.getNotifyResult() == null : this.getNotifyResult().equals(other.getNotifyResult()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notify
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNotifyId() == null) ? 0 : getNotifyId().hashCode());
        result = prime * result + ((getOrderRecordId() == null) ? 0 : getOrderRecordId().hashCode());
        result = prime * result + ((getNotifyCreateTime() == null) ? 0 : getNotifyCreateTime().hashCode());
        result = prime * result + ((getNotifyExpireTime() == null) ? 0 : getNotifyExpireTime().hashCode());
        result = prime * result + ((getNotifyType() == null) ? 0 : getNotifyType().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getNotifyResult() == null) ? 0 : getNotifyResult().hashCode());
        return result;
    }
}