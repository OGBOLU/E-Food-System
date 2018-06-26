package com.example.access_pc.login;

/**
 * Transition interface.
 * @author Jiaqi Zhang
 */
public interface Transition {

    /**
     * Transit to another activity.
     */
    void transit();

    /**
     * Show the toast.
     * @param str string
     */
    void setToast(String str);
}
