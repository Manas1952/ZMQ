import zmq
ctx = zmq.Context()
sock = ctx.socket(zmq.REP)
sock.bind("tcp://*:57000")
msg = sock.recv()