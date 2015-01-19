import os
import xmlrpclib
caller_id = '/script'
m = xmlrpclib.ServerProxy(os.environ['ROS_MASTER_URI'])
code, msg, val = m.getSystemState(caller_id)
if code == 1:
	pubs, subs, srvs = val
else:
	print "call failed", code, msg