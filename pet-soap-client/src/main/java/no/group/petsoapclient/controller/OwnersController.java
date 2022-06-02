package no.group.petsoapclient.controller;

import lombok.RequiredArgsConstructor;
import no.group.petsoapclient.client.OwnersClient;
import no.group.petsoapclient.model.OwnerModel;
import no.group.petsoapclient.model.PetModel;
import no.group.petsoapclient.model.VisitModel;
import no.group.petsoapclient.repository.TypeHardcodedRepository;
import no.group.petsoapclient.wsdl.OperationStatus;
import no.group.petsoapclient.wsdl.Owner;
import no.group.petsoapclient.wsdl.Pet;
import no.group.petsoapclient.wsdl.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnersController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final OwnersClient ownersClient;
    private final TypeHardcodedRepository typesRepo;

    @GetMapping("/list")
    public String listOwners(Model model) {
        List<OwnerModel> owners = ownersClient.getOwnersResponse();
        model.addAttribute("owners", owners);
        return "owners-list";
    }

    @GetMapping("/search")
    public String searchOwners(@RequestParam String keyword, Model model) {
        List<OwnerModel> owners = ownersClient.getOwnersResponse(keyword);
        model.addAttribute("owners", owners);
        return "owners-list";
    }

    @GetMapping("/delete/{ownerId}")
    public String deleteOwner(@PathVariable Integer ownerId) {
        OperationStatus status = ownersClient.deleteOwnerResponse(ownerId).getStatus();
        return "redirect:/owners/list";
    }

    @GetMapping("/info/{ownerId}")
    public String inspectOwner(@PathVariable Integer ownerId, Model model) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        model.addAttribute("owner", owner);
        return "owner-info";
    }

    @GetMapping("/showAddOwnerForm")
    public String showAddOwnerForm(Model model) {
        model.addAttribute("owner", new OwnerModel());
        return "add-owner-form";
    }

    @PostMapping("/saveOwner")
    public String saveOwner(@ModelAttribute OwnerModel owner) {
        OperationStatus status = ownersClient.saveOwnerResponse(owner).getStatus();
        return "redirect:/owners/list";
    }

    @PostMapping("/updateOwner")
    public String updateOwner(@ModelAttribute OwnerModel owner){
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+owner.getId();
    }

    @GetMapping("/showUpdateOwnerForm")
    public String showUpdateOwnerForm(@RequestParam Integer id ,Model model) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(id);
        model.addAttribute("owner", owner);
        return "update-owner-form";
    }

    @GetMapping("/deletePet")
    public String deletePet(@RequestParam Integer ownerId, @RequestParam Integer petId) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        owner.getPets().removeIf(pet -> pet.getId().intValue() == petId);
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+owner.getId();
    }

    @GetMapping("/deleteVisit")
    public String deleteVisit(@RequestParam Integer ownerId, @RequestParam Integer visitId) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        owner.getPets().forEach(pet -> pet.getVisits().removeIf(visit -> visit.getId().intValue() == visitId));
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+owner.getId();
    }

    @GetMapping("/showAddPetForm")
    public String showAddPetForm(@RequestParam Integer ownerId, Model model) {
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("pet", new PetModel());
        model.addAttribute("types", typesRepo.getTypes());
        return "pet-form";
    }

    @PostMapping("/savePet")
    public String savePet(@ModelAttribute PetModel pet, @ModelAttribute("ownerId") Integer ownerId) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        owner.getPets().removeIf(tempPet -> tempPet.getId().equals(pet.getId()));
        owner.getPets().add(pet);
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+ownerId;
    }

    @GetMapping("/showEditPetForm")
    public String showEditPetForm(@RequestParam Integer ownerId, @RequestParam Integer petId, Model model) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        PetModel pet = owner.getPets().stream().filter(tempPet -> petId.equals(tempPet.getId().intValue())).findFirst().get();
        model.addAttribute("pet", pet);
        model.addAttribute("types", typesRepo.getTypes());
        model.addAttribute("ownerId", ownerId);
        return "pet-form";
    }

    @GetMapping("/showAddVisitForm")
    public String showAddVisitForm(@RequestParam Integer ownerId, @RequestParam Integer petId, Model model) {
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("petId", petId);
        model.addAttribute("visit", new VisitModel());
        return "visit-form";
    }

    @GetMapping("/showEditVisitForm")
    public String showEditVisitForm(@RequestParam Integer ownerId, @RequestParam Integer petId,
                                    @RequestParam Integer visitId, Model model) {
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        PetModel pet = owner.getPets()
                .stream()
                .filter(tempPet -> petId.equals(tempPet.getId().intValue()))
                .findFirst().get();
        VisitModel visit = pet.getVisits()
                .stream()
                .filter(tempVisit -> visitId.equals(tempVisit.getId().intValue()))
                .findFirst().get();
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("petId", petId);
        model.addAttribute("visit", visit);
        return "visit-form";
    }

    @PostMapping("/saveVisit")
    public String saveVisit(@ModelAttribute VisitModel visit, @ModelAttribute("ownerId") Integer ownerId,
                            @ModelAttribute("petId") Integer petId){
        OwnerModel owner = ownersClient.getSingleOwnerResponse(ownerId);
        PetModel pet = owner.getPets()
                .stream()
                .filter(tempPet -> petId.equals(tempPet.getId().intValue()))
                .findFirst().get();
        pet.getVisits().removeIf(tempVisit -> tempVisit.getId().equals(visit.getId()));
        pet.getVisits().add(visit);
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+ownerId;
    }
}
