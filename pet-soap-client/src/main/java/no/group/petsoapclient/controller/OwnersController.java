package no.group.petsoapclient.controller;

import lombok.RequiredArgsConstructor;
import no.group.petsoapclient.client.OwnersClient;
import no.group.petsoapclient.wsdl.OperationStatus;
import no.group.petsoapclient.wsdl.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnersController {

    private final OwnersClient ownersClient;

    @GetMapping("/list")
    public String listOwners(Model model) {
        List<Owner> owners = ownersClient.getOwnersResponse().getOwners();
        model.addAttribute("owners", owners);
        return "owners-list";
    }

    @GetMapping("/search")
    public String searchOwners(@RequestParam String keyword, Model model) {
        List<Owner> owners = ownersClient.getOwnersResponse(keyword).getOwners();
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
        Owner owner = ownersClient.getSingleOwnerResponse(ownerId).getOwner();
        model.addAttribute("owner", owner);
        return "owner-info";
    }

    @GetMapping("/showAddOwnerForm")
    public String showAddOwnerForm(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "add-owner-form";
    }

    @PostMapping("/saveOwner")
    public String saveOwner(@ModelAttribute Owner owner) {
        OperationStatus status = ownersClient.saveOwnerResponse(owner).getStatus();
        return "redirect:/owners/list";
    }

    @PostMapping("/updateOwner")
    public String updateOwner(@ModelAttribute Owner owner){
        OperationStatus status = ownersClient.updateOwnerResponse(owner).getStatus();
        return "redirect:/owners/info/"+owner.getId();
    }

    @GetMapping("/showUpdateOwnerForm")
    public String showUpdateOwnerForm(@RequestParam Integer id ,Model model) {
        Owner owner = ownersClient.getSingleOwnerResponse(id).getOwner();
        model.addAttribute("owner", owner);
        return "update-owner-form";
    }

}
