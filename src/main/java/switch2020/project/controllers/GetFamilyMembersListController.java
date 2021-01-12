package switch2020.project.controllers;

import switch2020.project.model.Application;
import switch2020.project.services.FamilyService;
import switch2020.project.utils.FamilyMemberRelationDTO;

import java.util.ArrayList;
import java.util.List;


public class GetFamilyMembersListController {
    private Application FFMapp;

    public GetFamilyMembersListController(Application app) {
        this.FFMapp = app;
    }


    /**
     * Method that obtains the FFMapp, that has the FamilyService as
     * its attribute. Then uses a method of the FamilyService
     * which is the getDTOList, that iterates through the
     * specific family (familyID) obtains the necessary info from its familyMembers
     * and converts it to a DTO, returning it back here to the Controller
     * @param familyID representing the unique ID given to each family
     * @return DTOList with members name and the relation they have towards the Family Administrator
     */
    public List<FamilyMemberRelationDTO> getFamilyMemberAndRelation(int familyID, String adminCCNumber){
        FamilyService familyService = this.FFMapp.getFamilyService();
        return familyService.getDTOList(familyID, adminCCNumber);
    }

}
