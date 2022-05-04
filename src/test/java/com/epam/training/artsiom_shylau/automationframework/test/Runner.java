package com.epam.training.artsiom_shylau.automationframework.test;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.MachineClassVariants;
import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.service.*;

public class Runner {
    public static void main(String[] args) throws VariantSelectionException {
      //  System.out.println(DatacenterLocationVariants.valueOf("frankfut".toUpperCase()).getDatacenterLocationOptionId());
        String s = "1234{%s}";
        s = String.format(s, "tt");

        System.setProperty("environment", "dev");

        VirtualMachine virtualMachine = VirtualMachineCreator.createVirtualMachineWithDataFromProperty();
        GPU graphicProcessor = GPUCreator.createGPUWithDataFromProperty();
        LocalSSD localSSD = LocalSSDCreator.createLocalSSDWithDataFromProperty();
        Datacenter datacenter = DatacenterCreator.createDatacenterWithDataFromProperty();
        UsageTerm usageTerm = UsageTermCreator.createUsageTermWithDataFromProperty();

        System.out.println(datacenter.getLocation());

        String generatedAddress = "frekoutteyudda-2500@yopmail.com";

        System.out.println(generatedAddress.matches(".{5,}@\\w+\\.[a-z]{2,4}"));

       // System.out.println(OperationSystemVariants.getOperationSystemOptionIdByTextValue("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)"));

        System.out.println(MachineClassVariants.REGULAR.getMachineClassTextValue());

        System.out.println(MachineClassVariants.REGULAR.getMachineClassOptionId());

        //MachineClassVariants.getMachineClassOptionIdByTextValue("regular");



    }
}
