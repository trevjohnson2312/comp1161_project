#!/bin/python3

import math
import os
import random
import re
import sys
import datetime
# INCLUDE all functions for PROBLEM 1,2 and 3.
#
#

servicing_cost_list = ("CL", (["Honda", (45000.00, "0230"), (10000.00, "0030"), (70000.00, "0130")],\
                             ["BMW", (90000.00, "0330"), (30000.00, "0040"), (16000.00, "0050")],\
                             ["Ferrari", (245000.5, "0650"), (100000.00, "0030"), (134000.00, "0130")],\
                             ["Toyota", (30000.00, "0130"), (10000.00, "0100"), (15000.00, "0040")],\
                             ["Suzuki", (25000.00, "0200"), (8000.00, "0035"), (11000.00, "0020")],\
                             ["Benz", (150000.00, "0435"), (67000.00, "0030"), (7800.00, "0030")]))

def svlContents(serv_lst):
    return serv_lst[1]

def getMkSvl(serv_model):
    return serv_model[0]
  
#FH
def getFullHouseInfo(serv_lst,mk):
    for make in svlContents(serv_lst):
        if getMkSvl(make) == mk:
            return make[1]
#TY
def getTyreInfo(serv_lst,mk):
    for make in svlContents(serv_lst):
        if getMkSvl(make) == mk:
            return make[2]
#SH
def getShocksInfo(serv_lst,mk):
    for make in svlContents(serv_lst):
        if getMkSvl(make) == mk:
            return make[3]
    
def cost(activity_tuple):
    return activity_tuple[0]

def estTime(activity_tuple):
    return activity_tuple[1]

def getServCost(serv_lst,mk,activity):
    if activity == "FH":
        return cost(getFullHouseInfo(serv_lst,mk))
    elif activity == "SH":
        return cost(getShocksInfo(serv_lst,mk))
    elif activity == "TY":
        return cost(getTyreInfo(serv_lst,mk))

def getActivTime(serv_lst,mk,activity):
    if activity == "FH":
        return estTime(getFullHouseInfo(serv_lst,mk))
    elif activity == "SH":
        return estTime(getShocksInfo(serv_lst,mk))
    elif activity == "TY":
        return estTime(getTyreInfo(serv_lst,mk))

def getPayLimit(vhcl): 
    return int(vhcl[0])

def getMake(vhcl): 
    return vhcl[1][0]

def getModel(vhcl): 
    return vhcl[1][1]

def getYear(vhcl): 
    return int(vhcl[1][2])

def getMile(vhcl): 
    return int(vhcl[2])

def getLastServDate(vhcl): 
    return int(vhcl[3])

def getSerVCost(vhcl):
    return float(vhcl[4])


def makeCustomerRegister():
    return ('CR', [])

def contents(cr):
    return cr[1]

def addCustomer(custRec, cr):
    cr[1].append(custRec)
    return cr

def removeCustomer(cid, cr):
    for custRec in cr[1]:
        if custRec[0] == cid:
            cr[1].remove(custRec)
            break
    return cr


def isCustomerRegister(cr):
    return isinstance(cr, tuple) and len(cr) == 2 and cr[0] == 'CR' and isinstance(cr[1], list)


def isEmpty(cr):
    return len(cr[1]) == 0

def makeCustomerRecord(cid,hh,mm):
    return [cid, (hh,mm),(-1),[]]

def getCID(custRec):
    return custRec[0]

def getArivalTime(custRec):
    t1 = str(custRec[1][0])
    t2 = str(custRec[1][1])
    if len (t1) == 1:
        t1 = str(0) + t1
    return t1 + t2

def getServiceTime(custRec):
    return str(custRec[2])

def getCustType(custRec):
    return getCID(custRec)[0:2]

def getVehicle(custRec):
    return custRec[-1]

def updateVehicle(vchl,custRec):
    custRec.pop(-1)
    custRec.append(vchl)

def updateServiceTime(serviceTime,custRec):
    custRec.pop(2)
    custRec.insert(2,serviceTime)

def updateServiceCost(serviceCost,custRec):
    getVehicle(custRec).pop()
    getVehicle(custRec).append(serviceCost)

def generateCId(cname,cr,platnum):
    FirstName = ord(cname[0][0])
    LastName = ord (cname[1][0])
    full = FirstName + LastName
    if not platnum:
        cid = "NC" + str(full)
    else:
        cid = "PC" + str(full)
    while cid in cr:
        total += 1
    if not platnum:
        cid = "NC" + str(full)
    else:
        cid = "PC" + str(full)
    return cid

def addVehicle(cr,cid, Pl, Mk, Md, Y, Ml, LSD):
    if isCustomerRegister(cr):
        if not isEmpty(cr):
            vchl = [Pl, (Mk, Md, Y), Ml, LSD, 0]
            for custRec in contents(cr):
                if getCID(custRec) == cid:
                    updateVehicle(vchl, custRec)
    
def assessVehicle(cr, cid, servicing_cost_list):
    for i in contents(cr):
        if cid == getCID(i):
            SC = []
            Estimated_time = []
            a = datetime.datetime.now()
            currentdate = a.strftime("%Y")

            if (int(currentdate) - getYear(getVehicle(i))) > 5 and getMake(getVehicle(i)) != "Benz":
                SC += [getServCost(servicing_cost_list, getMake(getVehicle(i)), "FH")]
                Estimated_time += [int(getActivTime(servicing_cost_list, getMake(getVehicle(i)), 'FH'))]

            elif getMile(getVehicle(i)) > 100000:
                SC += [(getServCost(servicing_cost_list, getMake(getVehicle(i)), 'SH'))]
                Estimated_time += [int(getActivTime(servicing_cost_list, getMake(getVehicle(i)), "SH"))]

            if getLastServDate(getVehicle(i)) > 10:
                SC += [getServCost(servicing_cost_list, getMake(getVehicle(i)), "TY")]
                Estimated_time += [int(getActivTime(servicing_cost_list, getMake(getVehicle(i)), "TY"))]

            Total = sum(SC)
            PayLimit = getPayLimit(getVehicle(i)) * 1.05
            while Total > PayLimit:
                SC.remove(min(SC))
                Total = sum(SC)

            ServiceTime = str(sum(Estimated_time))

            while len(ServiceTime) < 4:
                ServiceTime = str(0) + str(ServiceTime)

            if int(ServiceTime[2:]) >= 60:
                mm = int(ServiceTime[2:]) -60
            if len(str(mm)) < 2:
                mm = str(0) + str(mm)
                hh =int(ServiceTime[:2]) + 1
            if len(str(hh)) < 2:
                hh = str(0) + str(hh)
                ServiceTime = str(hh) + str(mm)
            else:
                ServiceTime = ServiceTime.zfill(4)  # Add leading zeros if necessary

            Pickuptime = int(getArivalTime(i)) - 5 + int(ServiceTime)
            Pickuptime = str(Pickuptime).zfill(4)  # Add leading zeros if necessary
            
            if int(Pickuptime[2:]) >= 60:
                mm = int(Pickuptime[2:]) - 60
            
                if len(str(mm)) < 2:
                    mm = str(0) + str(mm)
                hh = int(Pickuptime[:2]) + 1
            
                if len(str(hh)) < 2:
                    hh = str(0) + str(hh)
                Pickuptime = str(hh) + str(mm)
            else:
                Pickuptime = Pickuptime.zfill(4)  # Add leading zeros if necessary


            updateServiceCost(float(Total), i)
            updateServiceTime(int(ServiceTime), i)
            return Pickuptime


if __name__ == '_main_':    
    no_of_customers = int(input())
    
    custReg = makeCustomerRegister()
    
    for i in range(no_of_customers):
        customer_info = input().strip().split(' ') 
        valz = int(customer_info[5])%100
        
        if isCustomerRegister(custReg):
            platinum = customer_info[0][0] == "P"
            addCustomer([customer_info[0],(int(customer_info[3]),int(customer_info[4])),-1,[customer_info[5], \
                        (customer_info[6], customer_info[7], customer_info[8]), \
                        customer_info[9], customer_info[10], 0] ], custReg)
            
            addVehicle(custReg,customer_info[0], customer_info[5],customer_info[6]\
                       ,customer_info[7],customer_info[8],(int(customer_info[9])+5)\
                       ,customer_info[10])
            
            print(assessVehicle(custReg, customer_info[0], servicing_cost_list))
            
    for cust in contents(custReg):
        print(getCID(cust), getArivalTime(cust), getServiceTime(cust), getCustType(cust), getVehicle(cust))