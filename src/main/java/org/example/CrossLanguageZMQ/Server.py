import zmq
ctx = zmq.Context()
sock = ctx.socket(zmq.REP)
sock.bind("tcp://*:5555")
msg = sock.recv()
print(msg)