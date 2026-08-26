import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 * CloudSimExample1 - Cloud Computing Lab Experiment 5
 *
 * Demonstrates a basic CloudSim simulation with:
 *   - 1 Datacenter
 *   - 1 Broker
 *   - 1 Virtual Machine
 *   - 1 Cloudlet (task)
 *
 * Based on CloudSim 3.0.3 examples.
 */
public class CloudSimExample1 {

    private static List<Cloudlet> cloudletList;
    private static List<Vm> vmList;

    public static void main(String[] args) {
        Log.printLine("Starting CloudSimExample1...");

        try {
            // Step 1: Initialize CloudSim
            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean traceFlag = false;

            Log.printLine("Initializing CloudSim...");
            CloudSim.init(numUsers, calendar, traceFlag);

            // Step 2: Create Datacenter
            Log.printLine("Creating Datacenter...");
            Datacenter datacenter = createDatacenter("Datacenter_0");

            // Step 3: Create Broker
            Log.printLine("Creating Broker...");
            DatacenterBroker broker = createBroker();
            int brokerId = broker.getId();

            // Step 4: Create Virtual Machine
            Log.printLine("Creating Virtual Machines...");
            vmList = new ArrayList<>();

            int vmId      = 0;
            int mips      = 1000;
            long size     = 10000; // image size (MB)
            int ram       = 512;   // VM memory (MB)
            long bw       = 1000;
            int pesNumber = 1;
            String vmm    = "Xen";

            Vm vm = new Vm(vmId, brokerId, mips, pesNumber, ram, bw, size,
                           vmm, new CloudletSchedulerTimeShared());
            vmList.add(vm);
            broker.submitVmList(vmList);

            // Step 5: Create Cloudlet (task)
            Log.printLine("Creating Cloudlets...");
            cloudletList = new ArrayList<>();

            int cloudletId     = 0;
            long length        = 400000;
            long fileSize      = 300;
            long outputSize    = 300;
            UtilizationModel utilizationModel = new UtilizationModelFull();

            Cloudlet cloudlet = new Cloudlet(cloudletId, length, pesNumber,
                                             fileSize, outputSize,
                                             utilizationModel,
                                             utilizationModel,
                                             utilizationModel);
            cloudlet.setUserId(brokerId);
            cloudlet.setVmId(vmId);
            cloudletList.add(cloudlet);
            broker.submitCloudletList(cloudletList);

            // Step 6: Start simulation
            Log.printLine("Starting CloudSim simulation...");
            CloudSim.startSimulation();
            CloudSim.stopSimulation();

            // Step 7: Print results
            List<Cloudlet> newList = broker.getCloudletReceivedList();
            printCloudletList(newList);

            Log.printLine("CloudSimExample1 finished!");

        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("Simulation terminated due to an unexpected error.");
        }
    }

    /**
     * Creates a simple Datacenter with one Host.
     */
    private static Datacenter createDatacenter(String name) throws Exception {

        List<Host> hostList = new ArrayList<>();

        // Processing Elements (CPU cores)
        List<Pe> peList = new ArrayList<>();
        int mips = 1000;
        peList.add(new Pe(0, new PeProvisionerSimple(mips)));

        // Host configuration
        int hostId  = 0;
        int ram     = 2048; // MB
        long storage = 1000000; // MB
        int bw      = 10000;

        hostList.add(new Host(
                hostId,
                new RamProvisionerSimple(ram),
                new BwProvisionerSimple(bw),
                storage,
                peList,
                new VmSchedulerTimeShared(peList)
        ));

        // Datacenter characteristics
        String arch      = "x86";
        String os        = "Linux";
        String vmm       = "Xen";
        double timeZone  = 10.0;
        double cost      = 3.0;
        double costPerMem = 0.05;
        double costPerStorage = 0.001;
        double costPerBw = 0.0;

        LinkedList<Storage> storageList = new LinkedList<>();

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, timeZone,
                cost, costPerMem, costPerStorage, costPerBw);

        return new Datacenter(name, characteristics,
                              new VmAllocationPolicySimple(hostList),
                              storageList, 0);
    }

    /**
     * Creates a DatacenterBroker.
     */
    private static DatacenterBroker createBroker() throws Exception {
        return new DatacenterBroker("Broker");
    }

    /**
     * Prints the Cloudlet results table.
     */
    private static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();

        String header = String.format("%-14s %-10s %-18s %-8s %-8s %-12s %-12s",
                "Cloudlet ID", "STATUS", "Data Center ID", "VM ID",
                "Time", "Start Time", "Finish Time");

        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine(header);

        for (int i = 0; i < size; i++) {
            Cloudlet cloudlet = list.get(i);
            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                String row = String.format("%-14d %-10s %-18d %-8d %-8.2f %-12.2f %-12.2f",
                        cloudlet.getCloudletId(),
                        "SUCCESS",
                        cloudlet.getResourceId(),
                        cloudlet.getVmId(),
                        cloudlet.getActualCPUTime(),
                        cloudlet.getExecStartTime(),
                        cloudlet.getFinishTime());
                Log.printLine(row);
            }
        }

        Log.printLine();
        Log.printLine("Simulation completed.");
    }
}
