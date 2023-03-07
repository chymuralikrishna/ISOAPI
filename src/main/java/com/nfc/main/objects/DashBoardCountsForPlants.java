package com.nfc.main.objects;

public class DashBoardCountsForPlants {

	private Integer managingPlantsPendingNCCount;
	private Integer NCCountSubmittedForApproval;
	private Integer correctiveActionForNCPendingCount;

	public Integer getManagingPlantsPendingNCCount() {
		return managingPlantsPendingNCCount;
	}

	public void setManagingPlantsPendingNCCount(Integer managingPlantsPendingNCCount) {
		this.managingPlantsPendingNCCount = managingPlantsPendingNCCount;
	}

	public Integer getNCCountSubmittedForApproval() {
		return NCCountSubmittedForApproval;
	}

	public void setNCCountSubmittedForApproval(Integer nCCountSubmittedForApproval) {
		NCCountSubmittedForApproval = nCCountSubmittedForApproval;
	}

	public Integer getCorrectiveActionForNCPendingCount() {
		return correctiveActionForNCPendingCount;
	}

	public void setCorrectiveActionForNCPendingCount(Integer correctiveActionForNCPendingCount) {
		this.correctiveActionForNCPendingCount = correctiveActionForNCPendingCount;
	}

}
