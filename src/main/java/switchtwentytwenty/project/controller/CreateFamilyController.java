package switchtwentytwenty.project.controller;

import switchtwentytwenty.project.dataaccesslayer.Application;
import switchtwentytwenty.project.dataaccesslayer.CreateFamilyService;
import switchtwentytwenty.project.dto.AddPersonDTO;
import switchtwentytwenty.project.dto.CreateFamilyDTO;

public class CreateFamilyController {

    private Application application;

    public CreateFamilyController(Application application) {
        this.application = application;
    }

    /**
     * Method to create a family and add a person as administrator
     * @param createFamilyDTO
     * @return True if Family successfully created and added. False (by Exception e catch) if anything fails validation. False (by boolean false return on line 24) if admin email is already registered.
     */
    public boolean createFamilyAndAdmin(CreateFamilyDTO createFamilyDTO, AddPersonDTO addPersonDTO) {
        boolean result;
        CreateFamilyService createFamilyService = new CreateFamilyService(createFamilyDTO, addPersonDTO, application);
        try {
            result = createFamilyService.createFamilyAndAddAdmin(); //False if email is already registered
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
