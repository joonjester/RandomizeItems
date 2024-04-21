app.use((req, res, next) => {
    const origin = req.get('Origin');
    // Check if the origin is allowed
    if (origin && (origin === 'http://example.com' || origin === 'http://localhost:8080')) {
        res.setHeader('Access-Control-Allow-Origin', origin);
        res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
        res.setHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    }
    next();
});

