k = tuple(map(int, input("k: ").split()))
board = [[0 for _ in range(8)] for _ in range(8)]

def move(k, visited):
    possible = [(2,1),(1,2),(-1,2),(-2,1),(-2,-1),(-1,-2),(1,-2),(2,-1)]
    moves = []
    for p in possible:
        new = (k[0]+p[0], k[1]+p[1])
        if 0 <= new[0] < 8 and 0 <= new[1] < 8 and new not in visited:
            moves.append(new)
    return moves

def open_tour(start):
    visited = set()
    path = []  
    k = start
    i = 1
    
    while True:
        board[k[0]][k[1]] = i
        visited.add(k)
        path.append(k)
        print(f"Step {i}: {k}")
        
        if len(visited) == 64:
            print("")
            for row in board:
                print(" ".join(f"{num:2}" for num in row))
            return path[-1]
        
        moves = move(k, visited)
        if not moves:
            while path and not moves:
                old = path.pop()
                board[old[0]][old[1]] = 0
                visited.remove(old)
                i -= 1
                if path:
                    k = path[-1]
                    moves = move(k, visited)
            if not moves:
                print("Dead-end reached. No solution from this start")
                return
            continue  
        
        minimum = 100
        for m in moves:
            deg = len(move(m, visited))
            if deg < minimum:
                k = m
                minimum = deg
        
        i += 1


def closed_tour(start,last):
    if(start in (move(last,set()))):
        print("Closed tour possible")
    else:
        print("Closed tour NOT possible")

last=open_tour(k)
closed_tour(k,last)

