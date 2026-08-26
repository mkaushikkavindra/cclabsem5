# Cloud Computing Lab Experiments (1–9)

This README consolidates Experiments 1 through 9 into a single document.

---

# Experiment 1 – Virtual Workstation

## Title
Virtual Workstation

## Aim
To install VirtualBox / VMware / equivalent open-source cloud workstation with different flavours of Linux or Windows OS on top of Windows 8 and above.

---

## Requirements
| Item | Details |
|------|---------|
| Host OS | Windows 8 / Windows 10 / Windows 11 (64-bit) |
| Software | Oracle VM VirtualBox (latest) |
| Guest OS ISO | Windows 98 (or any Linux/Windows ISO) |
| RAM (Host) | Minimum 2 GB (4 GB recommended) |
| Disk Space | Minimum 5 GB free |

---

## Introduction
**VirtualBox** is a free and open-source hosted hypervisor (Type-2) developed by Oracle. It allows users to run multiple guest operating systems simultaneously on a single physical host machine. This is widely used in cloud computing labs to simulate virtual workstations and isolated environments.

---

## Procedure

### A. Installing VirtualBox
1. Visit [https://www.virtualbox.org/](https://www.virtualbox.org/) and download the Windows Hosts installer.
2. Run the downloaded `.exe` file.
3. Click **Next** through the setup wizard.
4. Choose installation directory (default: `C:\Program Files\Oracle\VirtualBox\`).
5. Select required features (USB support, Networking, Python support).
6. Allow the installer to configure network interfaces when prompted.
7. Click **Install** and allow UAC permissions.
8. Click **Finish** — VirtualBox Manager launches.

### B. Creating a Virtual Machine
1. Click **New** in VirtualBox Manager.
2. Set the following configuration:
   - **Name:** Windows 98
   - **Type:** Microsoft Windows
   - **Version:** Windows 98
   - **ISO Image:** Browse to `windows98.iso`
3. Allocate hardware:
   - **Base Memory:** 512 MB
   - **Processors:** 1 CPU
4. Create a Virtual Hard Disk:
   - **Size:** 2.00 GB
   - **Type:** VDI (Dynamically Allocated)
5. Click **Finish** to create the VM.
6. Select the VM and click **Start**.
7. Follow the Windows 98 installer on-screen instructions.
8. Guest OS desktop loads after installation.

---

## Files
| File | Description |
|------|-------------|
| `setup.txt` | Step-by-step VirtualBox installation and VM configuration |
| `output.txt` | Simulated terminal/console output for this experiment |

---

## How to Execute
This is a configuration/installation experiment — no code to compile.
1. Follow the steps in `setup.txt`.
2. Install VirtualBox on the host machine.
3. Create and start the virtual machine using the steps provided.

---

## Expected Output
- VirtualBox Manager opens successfully.
- Virtual Machine (Windows 98) is created with 512 MB RAM, 1 CPU, 2 GB disk.
- VM starts and the guest OS desktop loads.

---

## Result
VirtualBox was installed successfully, and a virtual machine with Windows 98 as the guest OS was configured and executed on the host machine running Windows 8 or above.

---

# Experiment 2 – Virtual Machine: C Compiler

## Title
Virtual Machine – C Compiler

## Aim
To install a C compiler in the virtual machine created using VirtualBox and execute simple programs.

---

## Requirements
| Item | Details |
|------|---------|
| Virtual Machine | Tiny Core Linux (running in VirtualBox) |
| Compiler | compiletc (installed via tce-load) |
| Program | demo.c – Leap Year Checker |
| Language | C |

---

## Introduction
**Tiny Core Linux** is a minimal Linux distribution ideal for running inside a virtual machine. It uses the `tce-load` package manager to install software including C compilers. Once the compiler is installed, C programs can be compiled with the `cc` command and executed.

---

## Compiler Installation

Boot into Tiny Core Linux inside VirtualBox, then open the terminal and run:

```bash
tce-load -wi compiletc
```

This downloads and installs the GCC-based C compiler toolchain.

---

## Program – Leap Year Checker (`demo.c`)

```c
#include <stdio.h>

int main() {
    int y;

    printf("Enter year: ");
    scanf("%d", &y);

    if ((y % 400 == 0) || (y % 4 == 0 && y % 100 != 0))
        printf("%d is a Leap Year\n", y);
    else
        printf("%d is not a Leap Year\n", y);

    return 0;
}
```

### Logic
- Divisible by 400 → Leap Year
- Divisible by 4 but not 100 → Leap Year
- Otherwise → Not a Leap Year

---

## Compilation

```bash
cc demo.c
```

This compiles `demo.c` and produces the default output binary `a.out`.

---

## Execution

```bash
./a.out
```

---

## Input
```
1991
```

## Expected Output
```
Enter year: 1991
1991 is not a Leap Year
```

---

## Files
| File | Description |
|------|-------------|
| `demo.c` | C source code – Leap Year Checker |
| `output.txt` | Simulated terminal output |

---

## How to Execute
1. Start Tiny Core Linux virtual machine in VirtualBox.
2. Open terminal inside the VM.
3. Install compiler: `tce-load -wi compiletc`
4. Create/copy `demo.c` into the VM.
5. Compile: `cc demo.c`
6. Run: `./a.out`
7. Enter a year when prompted.

---

## Result
The C compiler was installed successfully inside the virtual machine, and the leap year C program was compiled and executed successfully.

---

# Experiment 3 – Google App Engine Hello World

## Title
Install Google App Engine. Create Hello World app and other simple web applications using Python/Java.

## Aim
To install Google App Engine, create a Hello World web application, and run simple web applications using Java.

---

## Requirements
| Item | Details |
|------|---------|
| IDE | Eclipse IDE for Java EE Developers |
| Plugin | Google Plugin for Eclipse |
| SDK | Google App Engine Java SDK |
| Language | Java (Servlet) |
| JDK | Java 7 or above |

---

## Introduction
**Google App Engine (GAE)** is a Platform-as-a-Service (PaaS) cloud offering by Google that allows developers to build and host web applications on Google's infrastructure. It supports Java, Python, PHP, and Go. In this experiment, a simple Hello World Java Servlet is created and run on the local GAE development server.

---

## Setup Steps

### 1. Install Google Plugin for Eclipse
- Open Eclipse → **Help → Eclipse Marketplace**
- Search: `Google Plugin for Eclipse`
- Click Install → Accept terms → Finish

### 2. Install Google App Engine SDK
- Download **App Engine Java SDK** from Google Cloud.
- Extract the SDK to a local folder (e.g., `C:\appengine-sdk\`).
- In Eclipse: **Window → Preferences → Google → App Engine**
- Add the SDK location.

### 3. Create a Web Application Project
- **File → New → Web Application Project**
- Project Name: `HelloWorld`
- Package: `com.example`
- Uncheck "Use Google Web Toolkit" (if not needed)
- Click **Finish**

---

## Project Structure
```
HelloWorld/
├── src/
│   └── HelloWorldServlet.java
├── war/
│   ├── index.html
│   └── WEB-INF/
│       ├── appengine-web.xml
│       └── web.xml
```

---

## Source Files

### HelloWorldServlet.java
A Java Servlet that responds to HTTP GET requests and outputs "Hello, world".

### appengine-web.xml
Configures the App Engine application ID, version, and threading model.

### web.xml
Maps the URL pattern `/helloworld` to the `HelloWorldServlet` class.

### index.html
Landing page with a link to the Hello World servlet.

---

## Running Locally
1. Right-click the project → **Run As → Web Application**
2. Eclipse starts the local development server.

### Expected URLs:
- Home page: `http://localhost:8888/`
- Servlet: `http://localhost:8888/helloworld`

---

## Expected Output
When visiting `http://localhost:8888/helloworld` in a browser:
```
Hello, world
```

---

## Files
| File | Description |
|------|-------------|
| `HelloWorldServlet.java` | Java Servlet – outputs Hello World |
| `appengine-web.xml` | App Engine configuration |
| `web.xml` | Web application deployment descriptor |
| `index.html` | Landing page |
| `output.txt` | Simulated server/browser output |

---

## Result
Google App Engine Hello World web application was created using Eclipse and the Java Servlet, and executed successfully on the local development server.

---

# Experiment 4 – GAE Launcher: Launch Web Applications

## Title
Use GAE Launcher to Launch Web Applications

## Aim
To use the GAE launcher to configure and launch web applications on Google App Engine.

---

## Requirements
| Item | Details |
|------|---------|
| Cloud Platform | Google App Engine (GAE) |
| SDK | Google Cloud SDK / GAE SDK |
| Configuration | app.yaml |
| Runtime | python27 (static file serving) |
| Files | index.html, style.css |

---

## Introduction
**Google App Engine (GAE)** provides a platform to host and serve web applications in the cloud. The **GAE SDK** includes a local development server (`dev_appserver.py`) and a launcher to test apps locally before deploying. The `app.yaml` file is the core configuration file that defines the runtime, URL handlers, and static file mappings.

---

## Project Structure
```
Exp 4/
├── app.yaml           ← App Engine configuration
├── deploy.txt         ← Deployment commands
└── www/
    ├── index.html     ← Main web page
    └── css/
        └── style.css  ← Stylesheet
```

---

## Configuration – app.yaml

The `app.yaml` file:
- Specifies `runtime: python27` for a Python 2.7 static app.
- Maps `/` to `www/index.html`.
- Maps all other URLs to files under `www/`.

```yaml
runtime: python27
api_version: 1
threadsafe: true

handlers:
- url: /
  static_files: www/index.html
  upload: www/index.html

- url: /(.*)
  static_files: www/\1
  upload: www/(.*)
```

---

## Web Page – www/index.html

A simple static HTML page:
```html
<html>
<head>
    <title>Hello, world!</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <h1>Hello, world!</h1>
    <p>This is a simple static HTML file served from Google App Engine.</p>
</body>
</html>
```

---

## Launching Locally

```bash
dev_appserver.py app.yaml
```

Access at: `http://localhost:8080/`

---

## Deployment to Google App Engine

```bash
gcloud app deploy
gcloud app browse
```

See `deploy.txt` for full deployment steps.

---

## Expected Output
When the app is running (locally or deployed):

```
Hello, world!

This is a simple static HTML file that will be served from Google App Engine.
```

---

## Files
| File | Description |
|------|-------------|
| `app.yaml` | App Engine application configuration |
| `deploy.txt` | Cloud SDK deployment commands |
| `www/index.html` | Static web page served by GAE |
| `www/css/style.css` | Stylesheet for the web page |
| `output.txt` | Simulated deployment and browser output |

---

## Result
The web application was successfully configured using `app.yaml` and launched using the GAE workflow. The application serves a static HTML page accessible via the browser.

---

# Experiment 5 – CloudSim Simulation

## Title
Simulate a Cloud Scenario Using CloudSim

## Aim
To simulate a cloud scenario using CloudSim and run a scheduling algorithm.

> **Note:** The manual's aim mentions "a scheduling algorithm not present in CloudSim". However, the actual procedure documented in the manual demonstrates setting up Eclipse with CloudSim 3.0.3 and running the built-in **CloudSimExample1.java**. No custom scheduling algorithm is documented in the provided manual procedure. `CloudSimExample1.java` included here follows the official CloudSim 3.0.3 example.

---

## Requirements
| Item | Version / Details |
|------|------------------|
| IDE | Eclipse IDE for Java Developers (64-bit) |
| Framework | CloudSim 3.0.3 |
| Math Library | Apache Commons Math 3.6.1 |
| JDK | Java 7 or above |
| OS | Windows (64-bit) |

---

## Introduction to CloudSim
**CloudSim** is an open-source simulation toolkit developed at the CLOUDS Lab, University of Melbourne. It enables researchers and developers to model and simulate cloud computing infrastructure and services — including datacenters, virtual machines, cloudlets (tasks), and scheduling policies — without requiring actual cloud hardware.

Key components:
- **CloudSim Core** – event-driven simulation engine
- **Datacenter** – simulates physical cloud infrastructure
- **DatacenterBroker** – manages VM and cloudlet submission
- **Vm** – represents a virtual machine
- **Cloudlet** – represents a task/job to be executed

---

## Setup Procedure

### Step 1: Download Eclipse
- Download **Eclipse IDE for Java Developers (Windows 64-bit)** from [https://www.eclipse.org/](https://www.eclipse.org/)

### Step 2: Download CloudSim 3.0.3
- Download from the CloudSim GitHub releases or the CLOUDS Lab website.
- Extract the ZIP to a local folder (e.g., `C:\cloudsim-3.0.3\`).

### Step 3: Download Apache Commons Math
- Download `commons-math3-3.6.1.jar` from [https://commons.apache.org/](https://commons.apache.org/)

### Step 4: Open Eclipse and Import Project
1. Launch `eclipse.exe`
2. **File → New → Project → Java Project**
3. Project Name: `CloudSim`
4. Uncheck "Use default location" → Browse to `C:\cloudsim-3.0.3\`
5. Click **Finish**

### Step 5: Add External JAR (if required)
1. Right-click project → **Build Path → Configure Build Path**
2. **Libraries** tab → **Add External JARs**
3. Browse to `commons-math3-3.6.1.jar` → Open → **OK**

### Step 6: Run CloudSimExample1
1. Navigate to: `examples → org.cloudbus.cloudsim.examples`
2. Open: `CloudSimExample1.java`
3. Run: **Run → Run** or `Ctrl + F11`
4. View output in Eclipse Console.

---

## Source File

`CloudSimExample1.java` demonstrates:
- Initializing CloudSim
- Creating a Datacenter with 1 Host
- Creating a Broker
- Creating 1 Virtual Machine
- Creating 1 Cloudlet (task)
- Starting the simulation
- Printing the results table

---

## How to Execute
1. Set up Eclipse with CloudSim 3.0.3 as described above.
2. Place `CloudSimExample1.java` in the examples package.
3. Run using `Ctrl + F11` in Eclipse.
4. Observe output in the Eclipse Console window.

---

## Expected Output
```
Starting CloudSimExample1...
Initializing CloudSim...
Creating Datacenter...
Creating Broker...
Creating Virtual Machines...
Creating Cloudlets...
Starting CloudSim simulation...

========== OUTPUT ==========
Cloudlet ID    STATUS     Data Center ID  VM ID   Time    Start Time  Finish Time
0              SUCCESS    2               0       0.40    0.10        0.50

Simulation completed.
CloudSimExample1 finished!
```

---

## Files
| File | Description |
|------|-------------|
| `CloudSimExample1.java` | CloudSim simulation source code |
| `output.txt` | Simulated Eclipse console output |

---

## Result
The CloudSim scenario was successfully simulated using Eclipse. The datacenter, broker, virtual machine, and cloudlet were configured and the simulation output was observed in the Eclipse console.

---

# Experiment 6 – Virtual Machine File Transfer

## Title
Find a Procedure to Transfer Files from One Virtual Machine to Another Virtual Machine

## Aim
To find a procedure to transfer files from one virtual machine to another virtual machine using VirtualBox.

---

## Requirements
| Item | Details |
|------|---------|
| Hypervisor | Oracle VM VirtualBox |
| Extension | VirtualBox Extension Pack (for USB) |
| Guest Additions | VirtualBox Guest Additions (for Drag & Drop / Shared Folders) |
| Host OS | Windows 8 / 10 / 11 |
| Guest OS | Any (Windows / Linux) |

---

## Introduction
In a virtualized environment, files cannot be directly copied between virtual machines (VMs) the same way they are on a physical network. VirtualBox provides several built-in mechanisms to enable file sharing and transfer between the host machine and guest VMs, and indirectly between two VMs.

---

## Method 1: Drag and Drop

**Tool Required:** VirtualBox Guest Additions  
**Direction:** Host ↔ Guest

### Steps:
1. Install Guest Additions: **Devices → Insert Guest Additions CD Image**
2. In the VirtualBox menu: **Devices → Drag and Drop → Bidirectional**
3. Drag a file from the host file explorer into the VM window (or vice versa).
4. To transfer between VM1 and VM2:
   - VM1 → Host (Guest to Host)
   - Host → VM2 (Host to Guest)

---

## Method 2: USB Drive

**Tool Required:** VirtualBox Extension Pack  
**Direction:** VM1 → USB → VM2

### Steps:
1. Install Extension Pack: **File → Preferences → Extensions → Add**
2. Connect USB drive to the host.
3. **VM Settings → USB → Enable USB Controller → Add USB Filter**
4. Select the USB drive.
5. Start VM1 — USB appears inside the guest OS.
6. Copy the file to the USB drive.
7. Remove the USB from VM1, attach to VM2.
8. Access the file from the USB inside VM2.

---

## Method 3: Shared Folder

**Tool Required:** VirtualBox Guest Additions  
**Direction:** Both VMs access a common host folder

### Steps:
1. Create a folder on the host: `C:\VMShare\`
2. **VM Settings → Shared Folders → + → Add Shared Folder**
   - Folder Path: `C:\VMShare\`
   - Folder Name: `VMShare`
   - Auto-mount: ✓
   - Make Permanent: ✓
3. Start the VM. The folder is mounted as:
   - Linux guest: `/media/sf_VMShare/`
   - Windows guest: Network Drive (e.g., `Z:\`)
4. Both VM1 and VM2 can read/write to this shared folder simultaneously.

---

## Files
| File | Description |
|------|-------------|
| `procedure.txt` | Detailed step-by-step procedure for all three methods |
| `output.txt` | Simulated file transfer output |

---

## How to Execute
This is a procedure-based experiment. Follow the steps in `procedure.txt` using Oracle VM VirtualBox. No programming or code compilation is required.

---

## Expected Output
- Files successfully transferred between the host and guest VMs.
- USB device visible inside the VM.
- Shared folder accessible by both VMs.

---

## Result
The procedure to transfer files between virtual machines was documented and executed successfully using three methods: Drag and Drop, USB Drive, and Shared Folder.

---

# Experiment 7 – Hadoop Single Node Cluster

## Title
Install Hadoop Single Node Cluster and Run Simple Applications like WordCount

## Aim
To find a procedure to set up a one-node Hadoop cluster.

---

## Requirements
| Item | Details |
|------|---------|
| OS | Ubuntu Linux (inside VirtualBox VM) |
| Java | OpenJDK 7 (java-7-openjdk) |
| Hadoop | 2.7.0 |
| SSH | openssh-server |
| Shell | Bash |

---

## Introduction to Hadoop

**Apache Hadoop** is an open-source framework for distributed storage and processing of large datasets using the MapReduce programming model. It is a core technology in Big Data and Cloud Computing.

### Hadoop Components:
| Component | Description |
|-----------|-------------|
| **Hadoop Common** | Shared utilities and libraries |
| **HDFS** | Hadoop Distributed File System – fault-tolerant storage |
| **YARN** | Yet Another Resource Negotiator – resource management |
| **MapReduce** | Distributed data processing framework |

---

## Procedure

### 1. Update System
```bash
sudo apt-get update
```

### 2. Install Java
```bash
sudo apt-get install openjdk-7-jdk
sudo apt-get install openjdk-7-jre
java -version
```

### 3. Install SSH Server
```bash
apt-get install openssh-server
ssh-keygen -t rsa -P "" -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
```

### 4. Create Hadoop User and Group
```bash
sudo addgroup hadoop
sudo adduser --ingroup hadoop hadoop
```

### 5. Download and Extract Hadoop 2.7.0
```bash
sudo tar -xzvf hadoop-2.7.0.tar.gz -C /usr/local/lib/
sudo chown -R hadoop:hadoop /usr/local/lib/hadoop-2.7.0
```

### 6. Create HDFS Directories
```bash
sudo mkdir -p /var/lib/hadoop/hdfs/namenode
sudo mkdir -p /var/lib/hadoop/hdfs/datanode
sudo chown -R hadoop /var/lib/hadoop
```

### 7. Configure Environment Variables (in `~/.bashrc`)
```bash
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export HADOOP_INSTALL=/usr/local/lib/hadoop-2.7.0
export PATH=$PATH:$HADOOP_INSTALL/sbin:$HADOOP_INSTALL/bin
```

Then:
```bash
source ~/.bashrc
```

### 8. Configure Hadoop XML Files
Edit:
- `core-site.xml` – HDFS address
- `hdfs-site.xml` – Replication, NameNode/DataNode directories
- `mapred-site.xml` – MapReduce framework
- `yarn-site.xml` – YARN services

See `setup.txt` for full XML configurations.

### 9. Format NameNode and Start Hadoop
```bash
hdfs namenode -format
start-dfs.sh
start-yarn.sh
jps
```

---

## Files
| File | Description |
|------|-------------|
| `setup.txt` | Detailed installation and configuration steps |
| `output.txt` | Simulated terminal output |

---

## Optional Supporting Example

> **Note:** The manual documents the Hadoop single-node setup procedure. The WordCount application is the standard first MapReduce example. If required by the instructor, a `WordCount.java` can be compiled and submitted as a jar using:
> ```bash
> hadoop jar wordcount.jar WordCount /input /output
> hdfs dfs -cat /output/part-r-00000
> ```

---

## How to Execute
This is a configuration/installation experiment.
1. Follow the steps in `setup.txt` on an Ubuntu VM.
2. Verify Hadoop is running with `jps`.
3. Access the web UI at `http://localhost:50070/`.

---

## Expected Output
```
java version "1.7.0_79"
Hadoop 2.7.0
NameNode, DataNode, SecondaryNameNode, ResourceManager, NodeManager (via jps)
```

---

## Result
The procedure for setting up a one-node Hadoop cluster was completed successfully. Java, SSH, the Hadoop user, HDFS directories, and environment variables were all configured.

---

# Experiment 8 – Creating and Executing Your First Docker Container

## Title
Creating and Executing Your First Container Using Docker

## Aim
To create and execute a Docker container using a Python program.

---

## Requirements
| Item | Details |
|------|---------|
| Platform | Docker Desktop (Windows / Linux / macOS) |
| Language | Python 3 |
| Base Image | python:latest (from Docker Hub) |
| Files | main.py, Dockerfile |

---

## Introduction
**Docker** is an open-source containerization platform that allows developers to package applications and their dependencies into lightweight, portable containers. Unlike virtual machines, Docker containers share the host OS kernel and are faster to start and more resource-efficient.

Key concepts:
| Term | Description |
|------|-------------|
| **Image** | A read-only template used to create containers |
| **Container** | A running instance of an image |
| **Dockerfile** | A text file with instructions to build an image |
| **Docker Hub** | Public registry for Docker images |

---

## Installation
1. Download Docker Desktop from [https://www.docker.com/](https://www.docker.com/)
2. Install and launch Docker Desktop.
3. Verify installation:
   ```bash
   docker --version
   ```

---

## Program – `main.py`

```python
#!/usr/bin/env python3
print("Docker is magic!")
```

A minimal Python 3 script that prints a message to the console.

---

## Dockerfile

```dockerfile
FROM python:latest
COPY main.py /
CMD [ "python", "./main.py" ]
```

| Instruction | Purpose |
|-------------|---------|
| `FROM python:latest` | Uses the latest official Python image as the base |
| `COPY main.py /` | Copies `main.py` from the host into the container root |
| `CMD [...]` | Runs `main.py` using Python when the container starts |

---

## Build the Docker Image

Navigate to the folder containing `Dockerfile` and `main.py`, then run:

```bash
docker build -t python-test .
```

- `-t python-test` — Tags the image with the name `python-test`
- `.` — Uses the current directory as the build context

---

## Run the Docker Container

```bash
docker run python-test
```

---

## Expected Output

```
Docker is magic!
```

---

## Useful Docker Commands

| Command | Purpose |
|---------|---------|
| `docker images` | List all images on the system |
| `docker ps` | List running containers |
| `docker ps -a` | List all containers (including stopped) |
| `docker rm <id>` | Remove a stopped container |
| `docker rmi <image>` | Remove an image |
| `docker logs <id>` | View container logs |

---

## Files
| File | Description |
|------|-------------|
| `main.py` | Python script executed inside the container |
| `Dockerfile` | Instructions to build the Docker image |
| `output.txt` | Simulated terminal output |

---

## How to Execute
1. Install Docker Desktop.
2. Open a terminal in the folder containing `Dockerfile` and `main.py`.
3. Build: `docker build -t python-test .`
4. Run: `docker run python-test`
5. Observe the output.

---

## Result
The first Docker container was created and executed successfully. The Python script ran inside the container and printed the expected output.

---

# Experiment 9 – Run a Container from Docker Hub

## Title
Run a Container from Docker Hub

## Aim
To run containers from Docker Hub using Docker CLI commands.

---

## Requirements
| Item | Details |
|------|---------|
| Platform | Docker Desktop (Windows / Linux / macOS) |
| Registry | Docker Hub (https://hub.docker.com/) |
| Containers | Ubuntu, Nginx, MongoDB |
| Network | Internet connection (to pull images) |

---

## Introduction

### Docker Hub
**Docker Hub** is the world's largest container image registry. It provides official and community-maintained images for operating systems, databases, web servers, and more. Docker automatically pulls images from Docker Hub when you run a container that is not yet cached locally.

### Docker CLI
The Docker Command Line Interface (CLI) allows users to manage containers, images, volumes, and networks from the terminal.

---

## Docker CLI Overview

```bash
docker -h
```
Displays all available Docker CLI commands and options.

---

## Experiment Steps

### Step 1: Run an Ubuntu Container

```bash
docker container run -it ubuntu top
```

- Downloads `ubuntu:latest` from Docker Hub (if not cached).
- Runs the `top` command (process list viewer) interactively inside the container.
- Press `q` to quit.

---

### Step 2: Run an Nginx Web Server Container

```bash
docker container run --detach --publish 8080:80 --name nginx nginx
```

| Flag | Meaning |
|------|---------|
| `--detach` | Run in background |
| `--publish 8080:80` | Expose container port 80 on host port 8080 |
| `--name nginx` | Assign the name "nginx" |

**Access in browser:** `http://localhost:8080`  
**Expected page:** *Welcome to nginx!*

---

### Step 3: Run a MongoDB Container

```bash
docker container run --detach --publish 8081:27017 --name mongo mongo:4.4
```

- Runs MongoDB 4.4 in the background.
- Accessible at `localhost:8081`.

---

### Step 4: List Running Containers

```bash
docker container ls
```

Shows all containers that are currently running.

---

### Step 5: Stop Containers

```bash
docker container stop nginx
docker container stop mongo
```

---

### Step 6: Clean Up

```bash
docker system prune
```

Removes all stopped containers, unused images, and build cache.

---

## Port Mapping Summary

| Container | Host Port | Container Port | URL |
|-----------|-----------|----------------|-----|
| nginx | 8080 | 80 | http://localhost:8080 |
| mongo | 8081 | 27017 | localhost:8081 |

---

## Files
| File | Description |
|------|-------------|
| `docker_commands.txt` | All Docker CLI commands used in this experiment |
| `output.txt` | Simulated terminal and browser output |

---

## How to Execute
1. Install Docker Desktop and ensure it is running.
2. Open a terminal (PowerShell / Command Prompt / Bash).
3. Run the commands listed in `docker_commands.txt` one by one.
4. Open `http://localhost:8080` in a browser to see the Nginx welcome page.

---

## Result
Containers were successfully pulled from Docker Hub and executed. The Ubuntu, Nginx, and MongoDB containers ran as expected, and the Nginx web server was accessible via the browser.

