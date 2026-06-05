package com.healthcaremanagement.util;

import java.util.ArrayList;
import java.util.List;
import com.healthcaremanagement.model.MetricCard;

/**
 * DashboardMetricsProvider - Provides role-specific dashboard metrics
 * 
 * Supplies metric cards based on user role (Admin, Doctor, Patient).
 */
public class DashboardMetricsProvider {
    
    /**
     * Gets metric cards for Admin role
     * 
     * @return List of MetricCard objects for admin dashboard
     */
    public static List<MetricCard> getAdminMetrics() {
        List<MetricCard> metrics = new ArrayList<>();
        metrics.add(new MetricCard("System Status", "Operational", StyleConstants.COLOR_SUCCESS));
        metrics.add(new MetricCard("Active Users", "14 Staff Online", StyleConstants.COLOR_SECONDARY));
        return metrics;
    }
    
    /**
     * Gets metric cards for Doctor role
     * 
     * @return List of MetricCard objects for doctor dashboard
     */
    public static List<MetricCard> getDoctorMetrics() {
        List<MetricCard> metrics = new ArrayList<>();
        metrics.add(new MetricCard("Today's Appointments", "6 Remaining", StyleConstants.COLOR_WARNING));
        metrics.add(new MetricCard("Pending Lab Reports", "3 Files", StyleConstants.COLOR_DANGER));
        return metrics;
    }
    
    /**
     * Gets metric cards for Patient role
     * 
     * @return List of MetricCard objects for patient dashboard
     */
    public static List<MetricCard> getPatientMetrics() {
        List<MetricCard> metrics = new ArrayList<>();
        metrics.add(new MetricCard("Medical Record", "Up to date", StyleConstants.COLOR_SUCCESS));
        metrics.add(new MetricCard("Next Checkup", "Tomorrow, 9 AM", StyleConstants.COLOR_INFO));
        return metrics;
    }
    
    /**
     * Gets metrics based on user role
     * 
     * @param role The user's role (Admin, Doctor, Patient)
     * @return List of MetricCard objects appropriate for the role
     */
    public static List<MetricCard> getMetricsForRole(String role) {
        if ("Admin".equalsIgnoreCase(role)) {
            return getAdminMetrics();
        } else if ("Doctor".equalsIgnoreCase(role)) {
            return getDoctorMetrics();
        } else if ("Patient".equalsIgnoreCase(role)) {
            return getPatientMetrics();
        }
        return new ArrayList<>();
    }
}
